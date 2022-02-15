package com.app.security.controller;

import com.app.security.dto.ProfileDto;
import com.app.security.dto.UserSummaryDto;
import com.app.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/profile")
    public ResponseEntity<ProfileDto> getUserProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getProfile(userId));
    }

    @PostMapping("/friends/{friendId}")
    public ResponseEntity<Void> addFriend(@PathVariable Long friendId) {
        userService.addFriend(friendId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<UserSummaryDto>> searchUsers(@RequestParam(value = "term") String term) {
        return ResponseEntity.ok(userService.searchUsers(term));
    }
}