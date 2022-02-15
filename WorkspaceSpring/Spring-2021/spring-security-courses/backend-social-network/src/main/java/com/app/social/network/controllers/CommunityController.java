package com.app.social.network.controllers;

import com.app.social.network.dto.MessageDto;
import com.app.social.network.services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/v1/community")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }


    @GetMapping("/messages")
    public ResponseEntity<?> getCommunityMessages(@RequestParam(value = "page", defaultValue = "0") int page)
    {
        return ResponseEntity.ok(communityService.getCommunityMessages(page));
    }

    @GetMapping("/images")
    public ResponseEntity<?> getCommunityImages(@RequestParam(value = "page", defaultValue = "0") int page)
    {
        return ResponseEntity.ok(communityService.getCommunityImages(page));
    }

    @PostMapping("/messages")
    public ResponseEntity<?> postMessage(@RequestBody MessageDto messageDto)
    {
        return ResponseEntity.created(URI.create("/v1/community/messages")).body(communityService.postMessage(messageDto));
    }

    @PostMapping("/images")
    public ResponseEntity<?> postImage(@RequestParam MultipartFile file, @RequestParam(value = "title") String title)
    {

        return ResponseEntity.created(URI.create("/v1/community/images")).body(communityService.postImage(file, title));
    }
}
