package com.manytomany.musiclibrary.artist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/music/artist")
public class ArtistController {

    @Autowired private ArtistService artistService;

    @PostMapping("/")
    public ArtistDto saveArtist(@RequestBody ArtistDto artistDto){
        return artistService.saveArtist(artistDto);
    }
    @GetMapping("/")
    public List<ArtistDto> getAllArtist(){
        return artistService.getAllArtistDetails();
    }

    @GetMapping("/getById/{id}")
    public ArtistDto getArtistById(@PathVariable int id){
        return artistService.getArtistById(id);
    }

    @GetMapping("/getArtistByTitle/{title}")
    public ArtistDto getArtistDetailsByTitle(@PathVariable String title){
        return artistService.getAristByTitle(title);
    }

    @PutMapping("/updateArtistById/{id}")
    public ArtistDto updateArtistById(@PathVariable int id, @RequestBody ArtistDto artistDto) throws Exception {
        return artistService.updateArtistById(id,artistDto);
    }

    @PutMapping("/updateArtistDetailsByTitle/{title}")
    public ArtistDto updateArtistDetailByTitle(@PathVariable String title, @RequestBody ArtistDto artistDto) throws Exception {
        return artistService.updateArtistByTitle(title,artistDto);
    }

    @DeleteMapping("/deleteArtistById/{id}")
    public void deleteArtistById(@PathVariable int id){
        artistService.deleteArtistById(id);
    }

    @DeleteMapping("/deleteArtistDetailsByTitle/{title}")
    public void deleteArtistDetailsByTitle(@PathVariable String title){
        artistService.deleteArtistByTitle(title);
    }

    @PostMapping("/{artistId}/addAlbum/{albumId}")
    public void addAlbumToArtist(@PathVariable int artistId, @PathVariable int albumId){
        artistService.addArtistToAlbum(artistId,albumId);
    }

    @DeleteMapping("/{artistId}/removeAlbum/{albumId}")
    public void removeAlbumFromArtist(@PathVariable int artistId, @PathVariable int albumId){
        artistService.removeAlbumFromArtist(artistId,albumId);
    }

    @GetMapping("/getAllAlbumsByArtistId/{id}")
    public List<ArtistDto> getAllByArtistId(@PathVariable int id){
        return artistService.findAllArtistByAlbumId(id);
    }
}
