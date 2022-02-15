package com.app.aws.service;

import com.app.aws.dto.RequestDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestInputService
{
    List<RequestDto> users = new ArrayList<>();

    public List<RequestDto> addUser(RequestDto requestDto)
    {
        users.add(requestDto);
        return users;
    }

    public List<RequestDto> getusers()
    {
        return users;
    }
}
