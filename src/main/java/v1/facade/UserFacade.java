package v1.facade;

import v1.exceptions.BadPasswordException;
import v1.exceptions.NameAlreadyUseException;
import v1.exceptions.UnknownUserException;
import v1.model.User;

public interface UserFacade {
	
	User register(String name, String password) throws NameAlreadyUseException;
	User login(String name, String password) throws UnknownUserException, BadPasswordException;
	
	User getUser(String name) throws UnknownUserException;

}
