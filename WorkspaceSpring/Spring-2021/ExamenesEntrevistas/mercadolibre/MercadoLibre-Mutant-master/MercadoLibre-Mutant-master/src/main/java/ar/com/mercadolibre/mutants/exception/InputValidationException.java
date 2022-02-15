package ar.com.mercadolibre.mutants.exception;

/**
 * Class that launchs an exception of the type InputValidationException
 * when a wrong ADN Sequence is loaded on the App,
 * which is called Alien ADN.
 *
 * @author vfuentes
 */
public class InputValidationException extends Exception {

    private String message;

    public InputValidationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}