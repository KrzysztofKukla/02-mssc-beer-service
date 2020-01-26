package pl.kukla.krzys.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.kukla.krzys.msscbeerservice.service.BeerService;
import pl.kukla.krzys.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

/**
 * @author Krzysztof Kukla
 */
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    private BeerService beerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private BeerDto validBeerDto;

    @BeforeEach
    void setUp() {
        validBeerDto = BeerDto.builder()
            .id(UUID.randomUUID())
            .beerName("first beer name")
            .build();
    }

    @Test
    void getBeerById() throws Exception {
        BDDMockito.given(beerService.getById(ArgumentMatchers.any(UUID.class))).willReturn(validBeerDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/{beerId}", UUID.randomUUID())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.beerName", Matchers.equalTo(validBeerDto.getBeerName())))
            .andReturn();

        BDDMockito.then(beerService).should().getById(ArgumentMatchers.any(UUID.class));
    }

    @Test
    void createNewBeer() throws Exception {
        BeerDto savedBeer = BeerDto.builder().id(UUID.randomUUID()).build();
        BDDMockito.given(beerService.saveBeer(ArgumentMatchers.any(BeerDto.class))).willReturn(savedBeer);
        BeerDto beerDto = validBeerDto;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(MockMvcResultMatchers.status().isCreated());

        BDDMockito.then(beerService).should().saveBeer(ArgumentMatchers.any(BeerDto.class));
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = validBeerDto;
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/{beerId}", UUID.randomUUID())
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
            .andExpect(MockMvcResultMatchers.status().isNoContent());

        BDDMockito.then(beerService).should().updateBeer(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(BeerDto.class));
    }

}