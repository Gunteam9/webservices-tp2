package facade;

import java.util.HashMap;
import java.util.Map;

import exceptions.NameAlreadyUseException;
import exceptions.UnknownUserException;
import model.User;

public class UserFacadeImpl implements UserFacade {
	
	private Map<String, User> registeredUsers;
	
	public UserFacadeImpl() {
		registeredUsers = new HashMap<String, User>();
	}

	@Override
	public User register(String name, String password) throws NameAlreadyUseException {
		if (registeredUsers.containsKey(name))
			throw new NameAlreadyUseException();
		User user = new User(registeredUsers.size(), name, password);
		registeredUsers.put(name, user);
		return user;
	}

	@Override
	public User login(String name, String password) throws UnknownUserException {
		if (!registeredUsers.containsKey(name))
			throw new UnknownUserException();
		return registeredUsers.get(name);
	}

}
