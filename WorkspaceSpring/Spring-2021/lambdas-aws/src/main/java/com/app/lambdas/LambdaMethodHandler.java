package com.app.lambdas;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.app.lambdas.dto.LambdaRequest;
import com.app.lambdas.dto.LambdaResponse;

public class LambdaMethodHandler implements RequestHandler<LambdaRequest, LambdaResponse>
{

	public LambdaResponse handleRequest(LambdaRequest request, Context context) 
	{
		LambdaResponse response = new LambdaResponse();	
		if(request != null && "1".equals(request.getOpcion()))
		{
			response.setNombre("Cristian");
			response.setApellido("Real");
		}
		else
		{
			response.setNombre("Daniel");
			response.setApellido("Ariza");
		}
		
		return response;
	}
	

}
