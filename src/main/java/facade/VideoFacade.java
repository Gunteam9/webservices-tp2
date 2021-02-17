package facade;

import java.net.URL;

public interface VideoFacade {
	
	long addVideo(long userId, URL url, String title, String description);
	long addVideo(long userId, String title, String description);

}
