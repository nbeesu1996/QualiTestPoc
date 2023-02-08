package com.qualitest.poc.config;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


@Component
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint, Serializable {

	Log logger = LogFactory.getLog(UnauthorizedEntryPoint.class);
	
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
    	logger.error("***********************************");
    	logger.error(authException.getCause());
    	logger.error(authException.fillInStackTrace());
    	authException.printStackTrace();
    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }

}