package pl.kukla.krzys.msscbeerservice.exception;

/**
 * @author Krzysztof Kukla
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        this.message = message;
    }

    private String message;

}
