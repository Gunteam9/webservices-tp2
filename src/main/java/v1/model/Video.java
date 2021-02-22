package v1.model;

import java.net.URL;

import v1.utils.Utils;

public class Video {
	
	private long id;
	private long userId;
	
	private URL url;
	private String title;
	private String description;

	public Video() {
	
	}
	
	public Video(long id, long userId, URL url, String titre, String description) {
		super();
		this.id = id;
		this.userId = userId;
		this.url = url;
		this.title = titre;
		this.description = description;
	}
	
	public Video(long id, long userId, String titre, String description) {
		super();
		this.id = id;
		this.userId = userId;
		this.url = Utils.randomURL();
		this.title = titre;
		this.description = description;
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

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



}
