package v1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import v1.facade.UserFacade;
import v1.model.User;

public class CustomUserDetailsService implements UserDetailsService {
	private static final String[] ROLES_ADMIN = {"USER", "ADMIN"};
	private static final String[] ROLES_USER = {"USER"};
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserFacade userFacade;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userFacade.getUser(username);
		if (user == null)
			throw new UsernameNotFoundException("User " + username + " not found");
		String[] roles = user.isAdmin() ? ROLES_ADMIN : ROLES_USER;
		UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
				.username(username)
				.password(passwordEncoder.encode(user.getPassword()))
				.roles(roles)
				.build();
		
		return userDetails;
	}
}
