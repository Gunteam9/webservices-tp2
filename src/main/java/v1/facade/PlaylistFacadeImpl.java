package v1.facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import v1.exceptions.UnknownPlaylistException;
import v1.model.Playlist;
import v1.model.Video;

@Component("PlaylistFacade")
public class PlaylistFacadeImpl implements PlaylistFacade {

	private long playlistId;
	private Map<Long, Playlist> playlists;
	
	public PlaylistFacadeImpl() {
		playlists = new HashMap<Long, Playlist>();
	}
	
	@Override
	public long createPlaylist(long userId, String name) {
		Playlist p = new Playlist(playlistId++, userId, name, new ArrayList<Video>());
		playlists.put(p.getId(), p);
		return p.getId();
	}

	@Override
	public void deletePlaylist(long id) throws UnknownPlaylistException {
		if (!playlists.containsKey(id))
			throw new UnknownPlaylistException();
		playlists.remove(id);
	}

	@Override
	public void addVideo(long id, Video... video) throws UnknownPlaylistException {
		if (!playlists.containsKey(id))
			throw new UnknownPlaylistException();
		playlists.get(id).addVideo(video);
	}
	
	@Override
	public void setVideo(long id, Video... video) throws UnknownPlaylistException {
		if (!playlists.containsKey(id))
			throw new UnknownPlaylistException();
		playlists.get(id).setVideos(List.of(video));
	}
	
	@Override
	public void setVideo(long id, List<Video> videos) throws UnknownPlaylistException {
		if (!playlists.containsKey(id))
			throw new UnknownPlaylistException();
		playlists.get(id).setVideos(videos);
	}

	@Override
	public void deleteVideo(long id, Video... videos) throws UnknownPlaylistException {
		if (!playlists.containsKey(id))
			throw new UnknownPlaylistException();
		playlists.get(id).deleteVideo(videos);
	}

	@Override
	public Playlist getPlaylist(long id) throws UnknownPlaylistException {
		if (!playlists.containsKey(id))
			throw new UnknownPlaylistException();
		
		return playlists.get(id);
	}
	
	@Override
	public Collection<Playlist> getPlaylist() {
		return playlists.values();
	}

}
