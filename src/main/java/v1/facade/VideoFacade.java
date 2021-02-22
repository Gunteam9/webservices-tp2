package v1.facade;

import java.net.URL;
import java.util.Collection;

import v1.model.Video;

public interface VideoFacade {
	
	long addVideo(long userId, URL url, String title, String description);
	long addVideo(long userId, String title, String description);
	
	Collection<Video> getVideos();

}
