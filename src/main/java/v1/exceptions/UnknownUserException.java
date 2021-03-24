package v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnknownUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3502720135200940182L;

	public UnknownUserException() {
		super("Unknown user");
		
	}

	public UnknownUserException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UnknownUserException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UnknownUserException(String message) {
		super(message);
		
	}

	public UnknownUserException(Throwable cause) {
		super(cause);
		
	}

	
	
}
