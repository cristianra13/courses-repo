package com.intraway.test.fizzbuzz.domain.service.impl;

import com.intraway.test.fizzbuzz.domain.exception.domain.MinGreaterThanMaxException;
import com.intraway.test.fizzbuzz.domain.exception.domain.UuidIsNotPresent;
import com.intraway.test.fizzbuzz.domain.models.ResponseFizzBuzz;
import com.intraway.test.fizzbuzz.domain.service.IFizzBuzzService;
import com.intraway.test.fizzbuzz.repository.FizzBuzzRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.Function;
import java.util.stream.IntStream;

import static com.intraway.test.fizzbuzz.domain.constant.ExceptionConstant.*;

@Service
public class FizzBuzzServiceImpl implements IFizzBuzzService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());
    private final FizzBuzzRepository repository;

    public final String DELIMITER = ",";

    @Autowired
    public FizzBuzzServiceImpl(FizzBuzzRepository repository) {
        this.repository = repository;
    }

    @Override
    public ResponseFizzBuzz validNumbers(String min, String max, String uuid) throws MinGreaterThanMaxException, UuidIsNotPresent {
        LOGGER.info("Starting process to uuid: " + uuid);
        if(uuid == null) {
            throw new UuidIsNotPresent(UUID_NOT_PRESENT);
        }
        int minNumber = castNumber(min, uuid);
        int maxNumber = castNumber(max, uuid);
        if(minNumber > maxNumber) {
            LOGGER.error(String.format("Error %s to uuid: %s", MIN_GREATER_THAN_MAX, uuid));
            throw new MinGreaterThanMaxException(MIN_GREATER_THAN_MAX);
        }
        StringJoiner listJoiner = new StringJoiner(DELIMITER);
        IntStream.rangeClosed(minNumber, maxNumber)
                .mapToObj(i -> isMultiplotOfBoth.apply(i) ? "FizzBuzz" :
                                isMultiplotOfFive.apply(i) ? "Buzz" :
                                isMultiplotOfThree.apply(i) ? "Fizz" : String.valueOf(i))
                .forEach(listJoiner::add);

        ResponseFizzBuzz responseFizzBuzz = buildResponse(min, max, listJoiner.toString(), uuid);
        LOGGER.info(String.format("Process successful to uuid: %s", uuid));
        repository.save(responseFizzBuzz);

        return responseFizzBuzz;
    }

    @Override
    public List<ResponseFizzBuzz> getAllFizzBuzz() {
        return repository.findAll();
    }

    private ResponseFizzBuzz buildResponse(String min, String max, String resultList, String uuid) {
        return new ResponseFizzBuzz(min, max, HttpStatus.OK.value(), messageResponse(resultList), uuid, resultList);
    }

    private String messageResponse(String resultList) {
        if (resultList.contains("FizzBuzz")) {
            return "multiples of 3 and 5 were found.";
        } else if (resultList.contains("Fizz")) {
            return "multiples of 3 were found.";
        } else if (resultList.contains("Buzz")) {
            return "multiples of 5 were found.";
        } else {
            return "multiples of 3 or 5 were not found.";
        }
    }

    private int castNumber(String number, String uuid) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            LOGGER.error(String.format("Error %s to uuid: %s", NOT_VALID_NUMBERS, uuid));
            throw new NumberFormatException(NOT_VALID_NUMBERS);
        }
    }

    Function<Integer, Boolean> isMultiplotOfThree = number -> number % 3 == 0;

    Function<Integer, Boolean> isMultiplotOfFive = number -> number % 5 == 0;

    Function<Integer, Boolean> isMultiplotOfBoth = number -> number % 3 == 0 && number % 5 == 0;
}
