package v1.facade;

import java.util.Collection;
import java.util.List;

import v1.exceptions.UnknownPlaylistException;
import v1.exceptions.UnknownUserException;
import v1.model.Playlist;
import v1.model.Video;

public interface PlaylistFacade {
	
	long createPlaylist(long userId, String name) throws UnknownUserException;
	void deletePlaylist(long id) throws UnknownPlaylistException;

	void addVideo(long id, Video... video) throws UnknownPlaylistException;
	void setVideo(long id, Video... video) throws UnknownPlaylistException;
	void setVideo(long id, List<Video> videos) throws UnknownPlaylistException;
	void deleteVideo(long id, Video...videos) throws UnknownPlaylistException;
	
	Playlist getPlaylist(long id) throws UnknownPlaylistException;
	Collection<Playlist> getPlaylist();

}
