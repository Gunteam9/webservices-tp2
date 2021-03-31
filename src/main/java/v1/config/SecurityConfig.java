package v1.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokens jwtTokens;
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.addFilter(new JwtAuthentificationFilter(authenticationManager(), jwtTokens))
		.addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtTokens))
		.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/video").permitAll()
		.antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
		.anyRequest().hasRole("USER")
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecretKey getSecretKey() {
		return Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
}
