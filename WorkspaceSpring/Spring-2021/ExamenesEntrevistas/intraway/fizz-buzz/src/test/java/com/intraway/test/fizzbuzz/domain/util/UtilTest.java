package com.intraway.test.fizzbuzz.domain.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    public void generateUuidNotEmptyTest() {
        assertFalse(Util.generateProcessUID().isEmpty());
    }

    @Test
    public void generateUuidEqualsTest() {
        assertNotEquals("123456789", Util.generateProcessUID().isEmpty());
    }

}