package com.app.security.controller;

import java.net.URI;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Stream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.app.security.config.CookieAuthenticationFilter;
import com.app.security.dto.SignUpDto;
import com.app.security.dto.UserDto;
import com.app.security.service.AuthenticationService;
import com.app.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(UserService userService,
                                    AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(@AuthenticationPrincipal UserDto user,
                                          HttpServletResponse servletResponse) {

        // creamos la cookie
        Cookie authCookie = new Cookie(CookieAuthenticationFilter.COOKIE_NAME, authenticationService.createToken(user));
        authCookie.setHttpOnly(true);
        authCookie.setSecure(true);
        authCookie.setMaxAge((int) Duration.of(1, ChronoUnit.DAYS).toSeconds());
        authCookie.setPath("/");

        servletResponse.addCookie(authCookie);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody @Validated SignUpDto user) {
        UserDto createdUser = userService.signUp(user);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId() + "/profile")).body(createdUser);
    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        Optional<Cookie> authCookie = Stream.of(Optional.ofNullable(request.getCookies()).orElse(new Cookie[0]))
                .filter(cookie -> CookieAuthenticationFilter.COOKIE_NAME.equals(cookie.getName()))
                .findFirst();
        authCookie.ifPresent(cookie -> cookie.setMaxAge(0));

        return ResponseEntity.noContent().build();
    }
}