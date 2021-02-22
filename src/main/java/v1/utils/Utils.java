package v1.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Utils {
	
	public static URL randomURL() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    try {
			return new URL(generatedString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
