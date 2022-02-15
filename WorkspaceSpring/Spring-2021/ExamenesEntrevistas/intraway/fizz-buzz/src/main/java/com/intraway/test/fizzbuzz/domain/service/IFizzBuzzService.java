package com.intraway.test.fizzbuzz.domain.service;

import com.intraway.test.fizzbuzz.domain.exception.domain.MinGreaterThanMaxException;
import com.intraway.test.fizzbuzz.domain.exception.domain.UuidIsNotPresent;
import com.intraway.test.fizzbuzz.domain.models.ResponseFizzBuzz;

import java.util.List;

public interface IFizzBuzzService {
    ResponseFizzBuzz validNumbers(String min, String String, String uuid) throws MinGreaterThanMaxException, UuidIsNotPresent;
    List<ResponseFizzBuzz> getAllFizzBuzz();
}
