package v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class BadTokenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5002741190996148203L;

	public BadTokenException() {
		super("Bad token exception");
		
	}

	public BadTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BadTokenException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BadTokenException(String message) {
		super(message);
		
	}

	public BadTokenException(Throwable cause) {
		super(cause);
		
	}

	

}
