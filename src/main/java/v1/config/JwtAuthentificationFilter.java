package v1.config;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
	private JwtTokens jwtTokens;
	
	public JwtAuthentificationFilter(AuthenticationManager authentificationManager, JwtTokens jwtTokens) {
		setAuthenticationManager(authentificationManager);
		setFilterProcessesUrl("/login");
		this.jwtTokens = jwtTokens;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
		UserDetails user = (UserDetails)authResult.getPrincipal();
		String token = jwtTokens.generateToken(user);
		
		response.addHeader(HttpHeaders.AUTHORIZATION, JwtTokens.PREFIX + token);
	}
}
