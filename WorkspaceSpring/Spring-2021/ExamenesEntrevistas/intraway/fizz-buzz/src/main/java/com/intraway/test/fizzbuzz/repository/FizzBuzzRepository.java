package com.intraway.test.fizzbuzz.repository;

import com.intraway.test.fizzbuzz.domain.models.ResponseFizzBuzz;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FizzBuzzRepository extends MongoRepository<ResponseFizzBuzz, String> {
}
