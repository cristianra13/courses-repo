package com.support.portal.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.support.portal.domain.HttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

import static com.support.portal.constant.SecurityConstant.ACCESS_DENIED_MESSAGE;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AccessDeniedException exception) throws IOException, ServletException {
        HttpResponse htppHttpResponse =
                new HttpResponse(
                        UNAUTHORIZED.value(),
                        UNAUTHORIZED,
                        UNAUTHORIZED.getReasonPhrase().toUpperCase(),
                        ACCESS_DENIED_MESSAGE);
        httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
        httpServletResponse.setStatus(UNAUTHORIZED.value());
        OutputStream outputStream = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, htppHttpResponse);
        outputStream.flush();
    }
}
