package v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnknownPlaylistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7100264390138819655L;

	public UnknownPlaylistException() {
		super("Unknown playlist");
		
	}

	public UnknownPlaylistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UnknownPlaylistException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UnknownPlaylistException(String message) {
		super(message);
		
	}

	public UnknownPlaylistException(Throwable cause) {
		super(cause);
		
	}

	
	
}
