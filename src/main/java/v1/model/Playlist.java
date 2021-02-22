package v1.model;

import java.util.List;

public class Playlist {

	private long id;
	private long userId;
	private String name;
	private List<Video> videos;
	
	public Playlist(long id, long userId, String name, List<Video> videos) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.videos = videos;
	}
	
	public void addVideo(Video... videos) {
		this.videos.addAll(List.of(videos));
	}
	
	public void deleteVideo(Video... videos) {
		this.videos.removeAll(List.of(videos));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	
}
