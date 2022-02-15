package com.intraway.test.fizzbuzz.domain.service.impl;

import com.intraway.test.fizzbuzz.domain.exception.domain.MinGreaterThanMaxException;
import com.intraway.test.fizzbuzz.domain.exception.domain.UuidIsNotPresent;
import com.intraway.test.fizzbuzz.domain.models.ResponseFizzBuzz;
import com.intraway.test.fizzbuzz.domain.service.IFizzBuzzService;
import com.intraway.test.fizzbuzz.domain.util.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FizzBuzzServiceImplTest {

    @Autowired
    private IFizzBuzzService service;

    @Test
    public void notEqualsResponseTest() throws MinGreaterThanMaxException, UuidIsNotPresent {
        assertNotEquals(new ResponseFizzBuzz(), service.validNumbers("0", "15", Util.generateProcessUID()));
    }

    @Test
    public void equalsResponseTest() throws MinGreaterThanMaxException, UuidIsNotPresent {
        String uud = Util.generateProcessUID();
        String resultList = "FizzBuzz,1,2,Fizz,4,Buzz,Fizz,7,8,Fizz,Buzz,11,Fizz,13,14,FizzBuzz";
        String min = "0";
        String max = "15";
        String messageExpected = "multiples of 3 and 5 were found.";
        ResponseFizzBuzz responseFizzBuzz =
                new ResponseFizzBuzz(min, max, HttpStatus.OK.value(), messageExpected, uud, resultList);
        responseFizzBuzz.setTimestamp(0L);

        ResponseFizzBuzz responseFizzBuzz1 = service.validNumbers("0", "15", uud);
        responseFizzBuzz1.setId(null);
        responseFizzBuzz1.setTimestamp(0L);
        assertEquals(responseFizzBuzz, responseFizzBuzz1);
    }

    @Test
    public void multiplesOnly3MsgTest() throws MinGreaterThanMaxException, UuidIsNotPresent {
        String uud = Util.generateProcessUID();
        String resultList = "1,2,Fizz,4,Buzz,Fizz,7,8,Fizz";
        String min = "1";
        String max = "9";
        String messageExpected = "multiples of 3 were found.";
        ResponseFizzBuzz responseFizzBuzz =
                new ResponseFizzBuzz(min, max, HttpStatus.OK.value(), messageExpected, uud, resultList);
        responseFizzBuzz.setTimestamp(0L);

        ResponseFizzBuzz responseFizzBuzz1 = service.validNumbers("1", "9", uud);
        responseFizzBuzz1.setId(null);
        responseFizzBuzz1.setTimestamp(0L);
        assertEquals(responseFizzBuzz, responseFizzBuzz1);
    }

    @Test
    public void multiplesOnly5MsgTest() throws MinGreaterThanMaxException, UuidIsNotPresent {
        String uud = Util.generateProcessUID();
        String resultList = "Buzz";
        String min = "10";
        String max = "10";
        String messageExpected = "multiples of 5 were found.";
        ResponseFizzBuzz responseFizzBuzz =
                new ResponseFizzBuzz(min, max, HttpStatus.OK.value(), messageExpected, uud, resultList);
        responseFizzBuzz.setTimestamp(0L);

        ResponseFizzBuzz responseFizzBuzz1 = service.validNumbers("10", "10", uud);
        responseFizzBuzz1.setId(null);
        responseFizzBuzz1.setTimestamp(0L);
        assertEquals(responseFizzBuzz, responseFizzBuzz1);
    }

    @Test
    public void numberFormatExceptionExpectedTest() {
        assertThrows(NumberFormatException.class, () -> {
            service.validNumbers(null, null, Util.generateProcessUID());
        });
    }

    @Test
    public void minGreaterThanMaxExceptionExpectedTest() {
        assertThrows(MinGreaterThanMaxException.class, () -> {
            service.validNumbers("10", "5", Util.generateProcessUID());
        });
    }

    @Test
    public void uuidIsNotPresentExceptionExpectedTest() {
        assertThrows(UuidIsNotPresent.class, () -> {
            service.validNumbers("10", "5", null);
        });
    }

}