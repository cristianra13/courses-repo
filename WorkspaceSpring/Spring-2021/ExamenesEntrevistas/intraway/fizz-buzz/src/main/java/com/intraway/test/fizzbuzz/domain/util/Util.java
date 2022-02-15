package com.intraway.test.fizzbuzz.domain.util;

import java.util.UUID;

public class Util {
    public static String generateProcessUID() {
        return UUID.randomUUID().toString();
    }
}
