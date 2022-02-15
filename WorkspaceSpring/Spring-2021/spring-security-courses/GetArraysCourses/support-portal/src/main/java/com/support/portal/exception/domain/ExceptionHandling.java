package com.support.portal.exception.domain;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.support.portal.domain.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

/**
 * Clase para manejar y capturar todas las excepciones de nuestra aplicación
 * <p>
 * Los demás controller's deben extender de está clase
 */

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact administration";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String ERROR_PROCESSING_FILE = "Error occurred while processing file";
    private static final String NOT_ENOUGH_PERMISSION = "You do not have enough permission";
    public static final String ERROR_PATH = "/error";

    /**
     * Método encargado de controllar las excepciones
     * de tipo DisabledException
     *
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    /**
     * Método encargado de controlar las excepciones
     * de tipo BadCredentialsException
     *
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    /**
     * Método encargado de controllar las excepciones
     * de tipo AccesDeniedException
     *
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, NOT_ENOUGH_PERMISSION);
    }

    /**
     *Método encargado de controllar las excepciones
     *de tipo LockedException
     *
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }

    /**
     *Método encargado de controllar las excepciones
     *de tipo TokenExpiredException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<HttpResponse> tokenExpiredException(TokenExpiredException exception) {
        return createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }

    /**
     *Método encargado de controllar las excepciones
     *de tipo EmailExistsException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(EmailExistsException.class)
    public ResponseEntity<HttpResponse> emailExistException(EmailExistsException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    /**
     *Método encargado de controllar las excepciones
     *de tipo UsernameExistsException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(UsernameExistsException.class)
    public ResponseEntity<HttpResponse> usernameExistException(UsernameExistsException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    /**
     *Método encargado de controllar las excepciones
     *de tipo EmailNotFoundException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<HttpResponse> emailNotFoundException(EmailNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    /**
     *Método encargado de controllar las excepciones
     *de tipo UserNotFoundException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpResponse> userNotFoundException(UserNotFoundException exception) {
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    /**
     * Método encargado de controlar las excepciones
     * de tipo NoHandlerFoundException
     *
     * Permite controlar el error 404
     *
     * @param e
     * @return ResponseEntity<HttpResponse>
     */
    //@ExceptionHandler(NoHandlerFoundException.class)
    //public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
    //    return createHttpResponse(BAD_REQUEST, "There is no mapping for this URL");
    //}

    /**
     *Método encargado de controllar las excepciones
     *de tipo HttpRequestMethodNotSupportedException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    /**
     * Método encargado de controllar las excepciones
     * de tipo Exception
     * Ayuda a controlar el error 500
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpResponse> internalServerErrorException(Exception exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }


    /**
     * Método encargado de controllar las excepciones
     * de tipo NotImageFileException, en caso de que
     * el archivo seleccionado no sea una imagen
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(NotAnImageFileException.class)
    public ResponseEntity<HttpResponse> notAnImageFileException(NotAnImageFileException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    /**
     * Método encargado de controllar las excepciones
     * de tipo NoResultException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    /**
     * Método encargado de controllar las excepciones
     * de tipo IOException
     *
     * @param exception
     * @return ResponseEntity<HttpResponse>
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        LOGGER.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    /**
     * Metodo de respuesta general, encargado de proveer
     * una firma generica para cada método de capura de
     * excepciones
     *
     * @param httpStatus
     * @param message
     * @return ResponseEntity<HttpResponse>
     */
    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        HttpResponse httpResponse =
                new HttpResponse(httpStatus.value(),
                        httpStatus,
                        httpStatus.getReasonPhrase().toUpperCase(),
                        message.toUpperCase());
        //return new ResponseEntity<>(httpResponse, httpStatus);
        return ResponseEntity.status(httpStatus).body(httpResponse);
    }

    /**
     * Método que nos permite controlar error 404
     * y lanzar un HttpResponse del paquete domain
     * con un mensaje personalizado
     *
     * @return ResponseEntity<HttpResponse>
     */
    @RequestMapping(ERROR_PATH)
    public ResponseEntity<HttpResponse> notFound404() {
        return createHttpResponse(NOT_FOUND, "There is no mapping for this URL");
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
