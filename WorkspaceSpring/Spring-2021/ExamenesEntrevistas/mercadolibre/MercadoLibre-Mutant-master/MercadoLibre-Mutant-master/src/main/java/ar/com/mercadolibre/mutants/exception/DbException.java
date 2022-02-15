package ar.com.mercadolibre.mutants.exception;

/**
 * Class that launchs an exception of the type DbException
 * when an Exception is launched from the Spring Boot Data Mongodb component
 *
 * @author vfuentes
 */
public class DbException extends Exception {

    private String message;

    public DbException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

