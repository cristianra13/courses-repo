package com.intraway.test.fizzbuzz.domain.exception.domain;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.intraway.test.fizzbuzz.domain.models.HttpResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.intraway.test.fizzbuzz.domain.constant.ExceptionConstant.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ExceptionHandling implements ErrorController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    /**
     * Método que controla excepciones de uuid
     * no presente
     *
     * @return
     */
    @ExceptionHandler(UuidIsNotPresent.class)
    public ResponseEntity<HttpResponse> uuidIsNoPresentException() {
        return createHttpResponse(BAD_REQUEST, NOT_VALID_NUMBERS);
    }

    /**
     * Metodo encargado de validar si un número es valido
     *
     * @return
     */
    @ExceptionHandler(IsNotValidNumber.class)
    public ResponseEntity<HttpResponse> isNotValidNumberException() {
        return createHttpResponse(BAD_REQUEST, NOT_VALID_NUMBERS);
    }

    /**
     * Método encragado de lanzar la excepción cuando
     * min sea mayor que max
     *
     * @return
     */
    @ExceptionHandler(MinGreaterThanMaxException.class)
    public ResponseEntity<HttpResponse> minGreaterThanMaxException() {
        return createHttpResponse(BAD_REQUEST, MIN_GREATER_THAN_MAX);
    }

    /**
     * Método encargado de lanzar la excepción
     * cuando haya error de casteo de números
     *
     * @return
     */
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<HttpResponse> castingNumberException() {
        return createHttpResponse(BAD_REQUEST, NOT_VALID_NUMBERS);
    }

    /**
     * Método encargado de controllar las excepciones
     * de tipo HttpRequestMethodNotSupportedException
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


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
