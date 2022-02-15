package com.lambda.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.lambda.aws.dto.RequestDto;
import com.lambda.aws.dto.ResponseDto;

public class EntryPointHandler implements RequestHandler<RequestDto, ResponseDto>
{
    @Override
    public ResponseDto handleRequest(RequestDto input, Context context)
    {
        return null;
    }
}
