package com.manytomany.musiclibrary.album;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import com.manytomany.musiclibrary.artist.Artist;
import com.manytomany.musiclibrary.artist.ArtistRepository;

@Service
public class AlbumService {

	@Autowired
	private AlbumRepository albumRepository;
	@Autowired
	private ArtistRepository artistRepository;
	private final EntityMapper entityMapper;

	public AlbumService(AlbumRepository albumRepository, ArtistRepository artistRepository, EntityMapper entityMapper) {
		this.albumRepository = albumRepository;
		this.artistRepository=artistRepository;
		this.entityMapper=entityMapper;
	}
	
	
	public AlbumDto saveAlbum(AlbumDto albumDto) {
		Album album = entityMapper.toEntity(albumDto);
		albumRepository.save(album);
		return entityMapper.toDto(album);
	}
	
	public List<AlbumDto> getAllAlbumDetails() {
		List<Album> albums = albumRepository.findAll();
		return albums.stream()
				.map(entityMapper::toDto)
				.collect(Collectors.toList());
	}
	
	public AlbumDto getAlbumById(int id) {
		Album album = albumRepository.findById(id).orElseThrow(() ->
				new IllegalArgumentException("Not album found with "+id));
		return entityMapper.toDto(album);
	}
	
	public AlbumDto getAlbumByAlbumName(String name) {
		Album album = albumRepository.findAlbumByName(name);
		return entityMapper.toDto(album);
	}


	public AlbumDto updateAlbumById(int id, AlbumDto albumDto) throws Exception {
		Album albumDetails = albumRepository.findById(id).orElseThrow(() ->new RuntimeException("Not found"));
		albumDetails.setName(albumDto.getName());
		albumDetails.setGenre(albumDto.getGenre());
		albumDetails.setDescription(albumDto.getDescription());

		try {
		 albumRepository.save(albumDetails);
		 return entityMapper.toDto(albumDetails);
		} catch(Exception e) {
			throw new SQLException(e);
		}
	}

	public void addAlbumToArtist(int albumId, int artistId){
		Album album = albumRepository.findById(albumId).orElseThrow(()->
				new RuntimeException("Album not found with id "+albumId));
		Artist artist = artistRepository.findById(artistId).orElseThrow(()->
				new RuntimeException("Artist not found with id "+artistId));
		album.getArtist().add(artist);
		albumRepository.save(album);
	}

	public void removeArtistFromAlbum(int albumId, int artistId) {
		Album album = albumRepository.findById(albumId).orElseThrow(() ->
				new RuntimeException("Album not found with id" +albumId));
		Artist artist = artistRepository.findById(artistId).orElseThrow(() ->
				new RuntimeException("Artist not found with id "+artistId));
		album.getArtist().remove(artist);
		albumRepository.save(album);
	}

	/*
	public Album updateAlbumDetailsByName(String name, Album album, String artistName) throws Exception {
		Album albumDetails = albumRepository.findAlbumByName(name);
		Set<Artist> artist = artistRepository.findArtistSetByTitle(artistName);
		albumDetails.setName(album.getName());
		albumDetails.setGenre(album.getGenre());
		albumDetails.setDescription(album.getDescription());
		albumDetails.setArtist(artist);
		try {
		return albumRepository.save(albumDetails);
		} catch(Exception e) {
			throw new SQLException(e);
		}
	}
	*/

	public void deleteById(int id) {
		Album album = albumRepository.getReferenceById(id);
		albumRepository.delete(album);
	}
/*
	public void deleteAlbumByName(String name) {
		Album album = albumRepository.findAlbumByName(name);
		albumRepository.delete(album);
	}
 */
	public List<AlbumDto> getAlbumByArtistId(int id){
		List<Album> albums = albumRepository.findAllByArtistId(id);

		return albums.stream()
				.map(entityMapper::toDto)
				.collect(Collectors.toList());
	}
	//*/
}
