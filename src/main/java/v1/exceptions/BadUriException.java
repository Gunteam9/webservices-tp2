package v1.exceptions;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BadUriException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4560281857566671782L;

	public BadUriException() {
		super("Bad URI");
		
	}

	public BadUriException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public BadUriException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public BadUriException(String message) {
		super(message);
		
	}

	public BadUriException(Throwable cause) {
		super(cause);
		
	}

	
	
}
