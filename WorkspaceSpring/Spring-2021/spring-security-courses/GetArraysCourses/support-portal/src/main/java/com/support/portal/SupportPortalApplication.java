package com.support.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

import static com.support.portal.constant.FileConstant.USER_FOLDER;

@SpringBootApplication
public class SupportPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupportPortalApplication.class, args);
        new File(USER_FOLDER).mkdirs();
    }

}
