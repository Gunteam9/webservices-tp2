package facade;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import model.Video;

public class VideoFacadeImpl implements VideoFacade {
	
	private Map<Long, Video> videos;
	
	public VideoFacadeImpl() {
		videos = new HashMap<Long, Video>();
	}

	@Override
	public long addVideo(long userId, URL url, String title, String description) {
		Video video = new Video(videos.size(), userId, url, title, description);
		videos.put(video.getId(), video);
		return video.getId();
	}

	@Override
	public long addVideo(long userId, String title, String description) {
		Video video = new Video(videos.size(), userId, title, description);
		videos.put(video.getId(), video);
		return video.getId();	
	}
}
