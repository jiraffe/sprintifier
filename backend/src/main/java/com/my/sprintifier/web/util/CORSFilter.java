package com.my.sprintifier.web.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (response instanceof HttpServletResponse) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.setHeader("Access-Control-Allow-Origin", "*");
			res.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST, HEAD, PUT, DELETE");
			res.setHeader("Access-Control-Max-Age", "3600");
			res.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, X-Requested-With,Set-Cookie,AccessToken,WWW-Authenticate,Authorization,Creds");
			res.setHeader("Access-Control-Allow-Credentials", "true");
			
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
}