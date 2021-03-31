package v1.config;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import v1.exceptions.BadTokenException;

@Component
public class JwtTokens {

	private static final long EXPIRATION_TIME = 86400;
	public static final String PREFIX = "Bearer ";
	
	@Autowired
	private Key secretKey;
	
	public String generateToken(UserDetails userDetails) {
		String login = userDetails.getUsername();
		List<String> roles = userDetails.getAuthorities().stream().map(auth -> auth.getAuthority()).collect(Collectors.toList());
		Claims claims = Jwts.claims().setSubject(login);
		claims.put("roles", roles);
		String token = Jwts.builder()
				.setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(secretKey)
				.compact();
		return token;
				
	}
	
	public UsernamePasswordAuthenticationToken decodeToken(String token) throws BadTokenException {
		if (token.startsWith(PREFIX))
			token = token.replaceFirst(PREFIX, "");
		
		try {
			Jws<Claims> jwsClaims = Jwts.parserBuilder()
					.setSigningKey(secretKey)
					.build()
					.parseClaimsJws(token);
			
			String login = jwsClaims.getBody().getSubject();
			List<String> roles = jwsClaims.getBody().get("roles", List.class);
			List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
			UsernamePasswordAuthenticationToken authentification = new UsernamePasswordAuthenticationToken(login, null, authorities);
			
			return authentification;
		} catch (JwtException e) {
			throw new BadTokenException();
		}
	}
}
