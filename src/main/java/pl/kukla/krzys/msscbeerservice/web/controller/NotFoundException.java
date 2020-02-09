package pl.kukla.krzys.msscbeerservice.web.controller;

/**
 * @author Krzysztof Kukla
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        this.message = message;
    }

    private String message;

}
