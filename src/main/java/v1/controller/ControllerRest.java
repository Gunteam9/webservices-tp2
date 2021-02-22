package v1.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import v1.exceptions.BadPasswordException;
import v1.exceptions.NameAlreadyUseException;
import v1.exceptions.UnknownPlaylistException;
import v1.exceptions.UnknownUserException;
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

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestHeader String name, @RequestHeader String password) {
		try {
			User user = userFacade.register(name, password);
			return ResponseEntity.status(HttpStatus.CREATED).body("Name: " + String.valueOf(user.getName()));
		} catch (NameAlreadyUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestHeader String name, @RequestHeader String password) {
		User user;
		try {
			user = userFacade.login(name, password);
			return ResponseEntity.status(HttpStatus.CREATED).header("id", String.valueOf(user.getId())).build();
		} catch (UnknownUserException e) {
			return new ResponseEntity("Unknown user", HttpStatus.NOT_FOUND);
		} catch (BadPasswordException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
	
	@GetMapping("/user/{name}")
	public ResponseEntity<User> getUserDetails(@PathVariable String name) {
		try {
			return ResponseEntity.ok(userFacade.getUser(name));
		} catch (UnknownUserException e) {
			return new ResponseEntity("Unknown user", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("user/{name}/video")
	public ResponseEntity<Collection<Video>> getUserVideo(@PathVariable String name) {
		try {
			User user = userFacade.getUser(name);
			return ResponseEntity.ok(videoFacade.getVideos().stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList()));
		} catch (UnknownUserException e) {
			return new ResponseEntity("Unknown user", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("user/{name}/video")
	public ResponseEntity<String> changeUserVideo(@PathVariable String name, @RequestBody Video video) {
		try {
			User user = userFacade.getUser(name);
			long videoId = videoFacade.addVideo(user.getId(), video.getUrl(), video.getTitle(), video.getDescription());
			return ResponseEntity.created(new URI("user/" + name + "/video/" + videoId)).build();
		} catch (UnknownUserException e) {
			return new ResponseEntity<String>("Unknown user", HttpStatus.NOT_FOUND);
		} catch (URISyntaxException e) {
			return new ResponseEntity<String>("Unknown playlist", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("user/{name}/playlist")
	public ResponseEntity<Collection<Playlist>> getPlaylist(@PathVariable String name) {
		try {
			User user = userFacade.getUser(name);
			return ResponseEntity.ok(playlistFacade.getPlaylist().stream().filter(o -> o.getUserId() == user.getId()).collect(Collectors.toList()));
		} catch (UnknownUserException e) {
			return new ResponseEntity("Unknown user", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("user/{name}/playlist/new/{playlistName}")
	public ResponseEntity<String> getPlaylist(@PathVariable String name, @PathVariable String playlistName) {
		try {
			User user = userFacade.getUser(name);
			long createdPlaylistId = playlistFacade.createPlaylist(user.getId(), playlistName);
			return ResponseEntity.created(new URI("user/" + name + "/playlist/" + createdPlaylistId)).build();
		} catch (UnknownUserException e) {
			return new ResponseEntity<String>("Unknown user", HttpStatus.NOT_FOUND);
		} catch (URISyntaxException e) {
			return new ResponseEntity<String>("Bad request \nError during URI creation", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("user/{name}/playlist/{id}")
	public ResponseEntity<String> changeUserPlaylist(@PathVariable String name, @PathVariable long id, @RequestBody Playlist playlist) {
		try {
			User user = userFacade.getUser(name);
			playlistFacade.setVideo(id, playlist.getVideos());
			return ResponseEntity.accepted().build();
		} catch (UnknownUserException e) {
			return new ResponseEntity<String>("Unknown user", HttpStatus.NOT_FOUND);
		} catch (UnknownPlaylistException e) {
			return new ResponseEntity<String>("Unknown playlist", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("user/{name}/playlist/{id}")
	public ResponseEntity<String> deleteUserPlaylist(@PathVariable String name, @PathVariable long id) {
		try {
			User user = userFacade.getUser(name);
			playlistFacade.deletePlaylist(id);
			return ResponseEntity.accepted().build();
		} catch (UnknownPlaylistException e) {
			return new ResponseEntity<String>("Unknown playlist", HttpStatus.NOT_FOUND);
		} catch (UnknownUserException e) {
			return new ResponseEntity<String>("Unknown user", HttpStatus.NOT_FOUND);
		}
	}
}
