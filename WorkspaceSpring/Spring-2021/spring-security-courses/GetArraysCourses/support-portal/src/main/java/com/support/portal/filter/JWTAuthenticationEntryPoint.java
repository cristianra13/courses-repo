package com.support.portal.filter;

import static com.support.portal.constant.SecurityConstant.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.support.portal.domain.HttpResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JWTAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    private static final Log logger = LogFactory.getLog(JWTAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) throws IOException {
        HttpResponse httpResponse =
                new HttpResponse(
                        FORBIDDEN.value(),
                        FORBIDDEN,
                        FORBIDDEN.getReasonPhrase().toUpperCase(),
                        FORBIDDEN_MESAGGE);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponse);
        // outputStream.flush();
    }
}
