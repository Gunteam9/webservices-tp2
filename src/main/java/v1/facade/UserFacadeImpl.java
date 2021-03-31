package v1.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import v1.exceptions.BadPasswordException;
import v1.exceptions.NameAlreadyUseException;
import v1.exceptions.UnknownUserException;
import v1.model.User;

@Component("UserFacade")
public class UserFacadeImpl implements UserFacade {
	
	private long userId;
	private Map<String, User> registeredUsers;
	
	public UserFacadeImpl() {
		registeredUsers = new HashMap<String, User>();
		
		User classic = register("test", "test");
		User admin = register("admin", "admin");
		admin.setAdmin(true);
	}

	@Override
	public User register(String name, String password) throws NameAlreadyUseException {
		if (registeredUsers.containsKey(name))
			throw new NameAlreadyUseException();
		User user = new User(userId++, name, password);
		registeredUsers.put(name, user);
		return user;
	}

	@Override
	public User login(String name, String password) throws UnknownUserException, BadPasswordException {
		if (!registeredUsers.containsKey(name))
			throw new UnknownUserException();
		User user = registeredUsers.get(name);
		if (password.equals(user.getPassword()))
			return user;
		else
			throw new BadPasswordException();
	}
	
	@Override
	public User getUser(String name) throws UnknownUserException {
		if (!registeredUsers.containsKey(name))
			throw new UnknownUserException();
		return registeredUsers.get(name);	
	}

}
