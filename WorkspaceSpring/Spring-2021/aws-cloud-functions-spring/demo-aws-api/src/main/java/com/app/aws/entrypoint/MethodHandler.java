package com.app.aws.entrypoint;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.app.aws.dto.RequestDto;
import com.app.aws.dto.ResponseDto;
import com.app.aws.service.RequestInputService;
import org.springframework.beans.factory.annotation.Autowired;

public class MethodHandler implements RequestHandler<RequestDto, ResponseDto>
{
    @Autowired
    private RequestInputService requestInputService;

    @Override
    public ResponseDto handleRequest(RequestDto input, Context context)
    {
        return new ResponseDto(null, 200, false, "All Ok");
    }
}
