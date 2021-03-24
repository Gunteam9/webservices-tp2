package v1.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import v1.exceptions.BadUriException;
import v1.facade.PlaylistFacade;
import v1.facade.UserFacade;
import v1.facade.VideoFacade;
import v1.model.Playlist;
import v1.model.User;
import v1.model.Video;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ControllerRest {
		
	@Autowired
	UserFacade userFacade;
	
	@Autowired
	VideoFacade videoFacade;
	
	@Autowired
	PlaylistFacade playlistFacade;
	
	@Autowired
	AppProperties appProperties;
	

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user, HttpServletRequest request) {
		try {
			user = userFacade.register(user.getName(), user.getPassword());
						
			HttpHeaders responseHeader = new HttpHeaders();
			responseHeader.setLocation(new URI("http://" + request.getRemoteHost() + "/user/" + user.getName()));
			
			return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeader).body(user);
		} catch (URISyntaxException e) {
			throw new BadUriException();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user, HttpServletRequest request) {
		try {
			user = userFacade.login(user.getName(), user.getPassword());
			
			HttpHeaders responseHeader = new HttpHeaders();
			responseHeader.setLocation(new URI("http://" + request.getRemoteHost() + "/user/" + user.getName()));
			
			return ResponseEntity.ok().headers(responseHeader).body(user);
		} catch (URISyntaxException e) {
			throw new BadUriException();
		}
	}
	
	@GetMapping("/video")
	public ResponseEntity<Collection<Video>> getVideos() {
		return ResponseEntity.ok(videoFacade.getVideos());
	}
	
	@GetMapping("/playlist")
	public ResponseEntity<Collection<Playlist>> getPlaylist() {
		return ResponseEntity.ok(playlistFacade.getPlaylist());
	}
	
	@GetMapping("/user/{name:[a-zA-Z]+}")
	public ResponseEntity<User> getUserDetails(@PathVariable String name) {
		return ResponseEntity.ok(userFacade.getUser(name));
	}
	
	@GetMapping("user/{name:[a-zA-Z]+}/video")
	public ResponseEntity<Collection<Video>> getUserVideo(@PathVariable String name) {
		User user = userFacade.getUser(name);
		return ResponseEntity.ok(videoFacade.getVideos().stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList()));
	}
	
	@PostMapping("user/{name:[a-zA-Z]+}/video")
	public ResponseEntity<Collection<Video>> changeUserVideo(@PathVariable String name, @RequestBody Video video, HttpServletRequest request) {
		try {
			User user = userFacade.getUser(name);
			long videoId = videoFacade.addVideo(user.getId(), video.getUrl(), video.getTitle(), video.getDescription());
			
			HttpHeaders responseHeader = new HttpHeaders();
			responseHeader.setLocation(new URI("http://" + request.getRemoteHost() + "/user/" + name + "/video/" + videoId));
			
			Collection<Video> userVideo = videoFacade.getVideos().stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList());
			
			return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeader).body(userVideo);
		} catch (URISyntaxException e) {
			throw new BadUriException();
		}
	}
	
	@GetMapping("user/{name:[a-zA-Z]+}/playlist")
	public ResponseEntity<Collection<Playlist>> getPlaylist(@PathVariable String name) {
		User user = userFacade.getUser(name);
		return ResponseEntity.ok(playlistFacade.getPlaylist().stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList()));
	}
	
	@PostMapping("user/{name:[a-zA-Z]+}/playlist/new/{playlistName}")
	public ResponseEntity<Collection<Playlist>> getPlaylist(@PathVariable String name, @PathVariable String playlistName, HttpServletRequest request) {
		try {
			User user = userFacade.getUser(name);
			long createdPlaylistId = playlistFacade.createPlaylist(user.getId(), playlistName);
			
			HttpHeaders responseHeader = new HttpHeaders();
			responseHeader.setLocation(new URI("http://" + request.getRemoteHost() + "/user/" + name + "/playlist/" + createdPlaylistId));
			
			Collection<Playlist> userPlaylist = playlistFacade.getPlaylist().stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList());
			
			return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeader).body(userPlaylist);
		} catch (URISyntaxException e) {
			throw new BadUriException();
		}
	}
	
	@PutMapping("user/{name:[a-zA-Z]+}/playlist/{id}")
	public ResponseEntity<String> changeUserPlaylist(@PathVariable String name, @PathVariable long id, @RequestBody Playlist playlist) {
		playlistFacade.setVideo(id, playlist.getVideos());
		return ResponseEntity.accepted().build();
	}
	
	@DeleteMapping("user/{name:[a-zA-Z]+}/playlist/{id}")
	public ResponseEntity<String> deleteUserPlaylist(@PathVariable String name, @PathVariable long id) {
		playlistFacade.deletePlaylist(id);
		return ResponseEntity.accepted().build();
	}
}
