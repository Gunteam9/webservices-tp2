package v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class BadPasswordException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6945947374895481382L;

	public BadPasswordException() {
		super("Bad password");
	}

	public BadPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BadPasswordException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BadPasswordException(String message) {
		super(message);
		
	}

	public BadPasswordException(Throwable cause) {
		super(cause);
		
	}

}
