package v1.facade;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import v1.model.Video;

@Component("VideoFacade")
public class VideoFacadeImpl implements VideoFacade {
	
	private long videosId = 0;
	private Map<Long, Video> videos;
	
	public VideoFacadeImpl() {
		videos = new HashMap<Long, Video>();
	}

	@Override
	public long addVideo(long userId, URL url, String title, String description) {
		Video video = new Video(videosId++, userId, url, title, description);
		videos.put(video.getId(), video);
		return video.getId();
	}

	@Override
	public long addVideo(long userId, String title, String description) {
		Video video = new Video(videosId++, userId, title, description);
		videos.put(video.getId(), video);
		return video.getId();	
	}
	
	@Override
	public Collection<Video> getVideos() {
		return videos.values();
	}
}
