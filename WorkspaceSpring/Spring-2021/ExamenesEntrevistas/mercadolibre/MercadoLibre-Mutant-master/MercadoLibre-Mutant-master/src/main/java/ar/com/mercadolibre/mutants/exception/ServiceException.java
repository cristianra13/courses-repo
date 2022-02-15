package ar.com.mercadolibre.mutants.exception;

/**
 * Class that launchs an exception of the type ServiceException
 * when the some Service Fails
 *
 * @author vfuentes
 */
public class ServiceException extends Exception {

    private String message;

    public ServiceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

