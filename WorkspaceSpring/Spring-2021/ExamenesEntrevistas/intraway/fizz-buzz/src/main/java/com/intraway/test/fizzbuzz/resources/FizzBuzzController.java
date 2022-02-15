package com.intraway.test.fizzbuzz.resources;

import com.intraway.test.fizzbuzz.domain.exception.domain.ExceptionHandling;
import com.intraway.test.fizzbuzz.domain.exception.domain.MinGreaterThanMaxException;
import com.intraway.test.fizzbuzz.domain.exception.domain.UuidIsNotPresent;
import com.intraway.test.fizzbuzz.domain.models.ResponseFizzBuzz;
import com.intraway.test.fizzbuzz.domain.service.IFizzBuzzService;
import com.intraway.test.fizzbuzz.domain.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/intraway/api/fizzbuzz")
public class FizzBuzzController extends ExceptionHandling {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private IFizzBuzzService service;

    @GetMapping("/{min}/{max}")
    public ResponseEntity<?> validateNumbers(@PathVariable  String min, @PathVariable String max)
            throws MinGreaterThanMaxException, UuidIsNotPresent {
        String uuid = Util.generateProcessUID();
        ResponseFizzBuzz responseFizzBuzz = service.validNumbers(min, max, uuid);
        LOGGER.info(String.format("Sending response to uuid: %s", responseFizzBuzz.getUuid()));
        return ResponseEntity.ok(responseFizzBuzz);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listFizzBuzz() {
        List<ResponseFizzBuzz> allFizzBuzz = service.getAllFizzBuzz();
        if (allFizzBuzz.size() == 0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(allFizzBuzz);
    }
}
