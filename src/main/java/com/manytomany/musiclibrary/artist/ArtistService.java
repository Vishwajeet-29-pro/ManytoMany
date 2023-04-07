package com.manytomany.musiclibrary.artist;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.manytomany.musiclibrary.album.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manytomany.musiclibrary.album.Album;
import com.manytomany.musiclibrary.album.AlbumRepository;

@Service
public class ArtistService {

	@Autowired private ArtistRepository artistRepository;
	@Autowired private AlbumRepository albumRepository;
	private final EntityMapper entityMapper;

	public ArtistService(ArtistRepository artistRepository, AlbumRepository albumRepository, EntityMapper entityMapper) {
		this.artistRepository = artistRepository;
		this.albumRepository = albumRepository;
		this.entityMapper = entityMapper;

	}
	
	public ArtistDto saveArtist(ArtistDto artistDto) {
		Artist artist = entityMapper.toEntity(artistDto);
		artistRepository.save(artist);
		return entityMapper.toDto(artist);
	}
	
	public ArtistDto getArtistById(int id) {
		Artist artist = artistRepository.findById(id).orElseThrow(() ->
				new RuntimeException("Artist not found with "+id));
		return entityMapper.toDto(artist);
	}
	
	public ArtistDto getAristByTitle(String title) {
		Artist artist = artistRepository.findArtistByTitle(title);
		return entityMapper.toDto(artist);
	}
	
	public List<ArtistDto> getAllArtistDetails(){
		List<Artist> artists = artistRepository.findAll();
		return artists.stream()
				.map(entityMapper::toDto)
				.collect(Collectors.toList());
	}

	public ArtistDto updateArtistById(int id, ArtistDto artistDto) throws Exception {
		Artist artistDetails = artistRepository.getReferenceById(id);
		artistDetails.setTitle(artistDto.getTitle());
		artistDetails.setReleaseDate(artistDto.getReleaseDate());
		artistDetails.setCoverArt(artistDto.getCoverArt());
		artistDetails.setDescription(artistDto.getDescription());
		try {
			artistRepository.save(artistDetails);
			return entityMapper.toDto(artistDetails);
	}catch(Exception e) {
		throw new SQLException(e);
	}
	}
	
	public ArtistDto updateArtistByTitle(String title, ArtistDto artistDto) throws Exception {
		Artist artistDetails = artistRepository.findArtistByTitle(title);
		artistDetails.setTitle(artistDto.getTitle());
		artistDetails.setReleaseDate(artistDto.getReleaseDate());
		artistDetails.setCoverArt(artistDto.getCoverArt());
		artistDetails.setDescription(artistDto.getDescription());
		
		try {
			artistRepository.save(artistDetails);
			return entityMapper.toDto(artistDetails);
		}catch(Exception e) {
			throw new SQLException(e);
		}
	}
	
	public void deleteArtistById(int id) {
		Artist artist = artistRepository.getReferenceById(id);		
		artistRepository.delete(artist);
	}
	
	public void deleteArtistByTitle(String title) {
		Artist artist = artistRepository.findArtistByTitle(title);
		artistRepository.delete(artist);
	}

	public void addArtistToAlbum(int artistId, int albumId) {
		Artist artist = artistRepository.findById(artistId).orElseThrow(() ->
				new RuntimeException("Artist Not found with Id "+artistId));
		Album album = albumRepository.findById(albumId).orElseThrow(() ->
				new RuntimeException("Album Not found with Id "+albumId));
		artist.getAlbum().add(album);
		artistRepository.save(artist);
	}

	public void removeAlbumFromArtist(int artistId, int albumId){
		Artist artist = artistRepository.findById(artistId).orElseThrow(()->
				new RuntimeException("Artist not found with id "+artistId));
		Album album = albumRepository.findById(albumId).orElseThrow(() ->
				new RuntimeException("Album not found with id "+albumId));
		artist.getAlbum().remove(album);
		artistRepository.save(artist);
	}

	public List<ArtistDto> findAllArtistByAlbumId(int artistId){
		List<Artist> artists = artistRepository.findAllByAlbumId(artistId);
		return artists.stream()
				.map(entityMapper::toDto)
				.collect(Collectors.toList());
	}
}

