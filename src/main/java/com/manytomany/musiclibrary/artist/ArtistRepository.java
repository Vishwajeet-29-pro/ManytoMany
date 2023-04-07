package com.manytomany.musiclibrary.artist;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

	public Artist findArtistByTitle(String title);

	@Query("SELECT a FROM Artist a JOIN a.albums album WHERE album.id = :albumId")
	List<Artist> findAllByAlbumId(@Param("albumId") int albumId);

}
