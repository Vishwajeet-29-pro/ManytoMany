package com.manytomany.musiclibrary.album;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.manytomany.musiclibrary.artist.ArtistService;

@RestController
@RequestMapping("/api/1.0/music/album")
public class AlbumController {

	@Autowired private AlbumService albumService;
	@Autowired private ArtistService artistService;
	
	
	@PostMapping("/")
	public ResponseEntity<AlbumDto> saveAlbum(@RequestBody AlbumDto albumDto){
		AlbumDto savedAlbumDto = albumService.saveAlbum(albumDto);
		return ResponseEntity.created(URI.create("/musics/" + savedAlbumDto.getId())).body(savedAlbumDto);
	}
	@GetMapping("/")
	public List<AlbumDto> getAllAlbums(){
		return albumService.getAllAlbumDetails();
	}
	
	@GetMapping("/{id}")
	public AlbumDto getAlbumById(@PathVariable("id") int id) {
		return albumService.getAlbumById(id);
	}
	
	@GetMapping("/getByName/{name}")
	public AlbumDto getAlbumDetailByName(@PathVariable("name") String name) {
		return albumService.getAlbumByAlbumName(name);
	}
	
	@PutMapping("/updateAlbumDetailsById/{id}")
	public AlbumDto updateAlbumDetailsById(@PathVariable int id, @RequestBody AlbumDto albumDto) throws Exception {
		return albumService.updateAlbumById(id, albumDto);
	}

	@DeleteMapping("/deleteAlbumDetailsById/{id}")
	public void deleteAlbumDetailsById(@PathVariable int id) {
		albumService.deleteById(id);
	}

	@PostMapping("/{albumId}/addArtist/{artistId}")
	public void addAlbumToArtist(@PathVariable int albumId, @PathVariable int artistId){
		albumService.addAlbumToArtist(albumId,artistId);
	}

	@DeleteMapping("/{albumId}/removeArtist/{artistId}")
	public void removeAlbumFromArtist(@PathVariable int albumId, @PathVariable int artistId){
		albumService.removeArtistFromAlbum(albumId,artistId);
	}

	@GetMapping("/getAllAlbumByArtistId/{id}")
	public List<AlbumDto> getAlbumByArtistId(@PathVariable int id){
		return albumService.getAlbumByArtistId(id);
	}

}
