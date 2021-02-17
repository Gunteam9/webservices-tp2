package facade;

import exceptions.UnknownUserException;
import exceptions.UnknownPlaylistException;
import model.Playlist;
import model.Video;

public interface PlaylistFacade {
	
	long createPlaylist(long userId, String name) throws UnknownUserException;
	void deletePlaylist(long id) throws UnknownPlaylistException;

	void addVideo(long id, Video... video) throws UnknownPlaylistException;
	void setVideo(long id, Video... video) throws UnknownPlaylistException;
	void deleteVideo(long id, Video...videos) throws UnknownPlaylistException;
	
	Playlist getPlaylist(long id) throws UnknownPlaylistException;

}
