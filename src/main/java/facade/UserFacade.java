package facade;

import exceptions.NameAlreadyUseException;
import exceptions.UnknownUserException;
import model.User;

public interface UserFacade {
	
	User register(String name, String password) throws NameAlreadyUseException;
	User login(String name, String password) throws UnknownUserException;

}
