package com.manytomany.musiclibrary.album;

import com.manytomany.musiclibrary.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

	public Album findAlbumByName(String name);

   @Query("SELECT a FROM Album a JOIN a.artists artist WHERE artist.id = :artistId")
   List<Album> findAllByArtistId(@Param("artistId") int artistId);
}
