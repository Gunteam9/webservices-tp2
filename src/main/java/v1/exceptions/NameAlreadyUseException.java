package v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class NameAlreadyUseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4962307000552406021L;

	public NameAlreadyUseException() {
		super("Name already used");
		
	}

	public NameAlreadyUseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public NameAlreadyUseException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NameAlreadyUseException(String message) {
		super(message);
		
	}

	public NameAlreadyUseException(Throwable cause) {
		super(cause);
		
	}
	
	

}
