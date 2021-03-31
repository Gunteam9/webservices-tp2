package v1.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import v1.exceptions.BadTokenException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
	private JwtTokens jwtTokens;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, JwtTokens jwtTokens) {
		super(authenticationManager);
		this.jwtTokens = jwtTokens;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (token == null)
			SecurityContextHolder.clearContext();
		else {
			UsernamePasswordAuthenticationToken authentication = null;
			try {
				authentication = jwtTokens.decodeToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (BadTokenException e) {
				SecurityContextHolder.clearContext();
			}
		}
		
		chain.doFilter(request, response);
	}
}
