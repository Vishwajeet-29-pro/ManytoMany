package com.manytomany.musiclibrary.album;

import com.manytomany.musiclibrary.artist.Artist;
import com.manytomany.musiclibrary.artist.ArtistDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EntityMapperImpl implements EntityMapper {
    @Override
    public AlbumDto toDto(Album album) {
        return new AlbumDto(album.getId(),album.getName(),album.getGenre(),album.getDescription());
    }

    @Override
    public ArtistDto toDto(Artist artist) {

        return new ArtistDto(artist.getId(),artist.getTitle(),artist.getReleaseDate(),artist.getCoverArt(), artist.getDescription());
    }

    @Override
    public Album toEntity(AlbumDto albumDto) {
        return new Album(albumDto.getId(),albumDto.getName(),albumDto.getGenre(),albumDto.getDescription());
    }

    @Override
    public Artist toEntity(ArtistDto artistDto) {
        return new Artist(artistDto.getId(),artistDto.getTitle(), artistDto.getReleaseDate(),artistDto.getCoverArt(), artistDto.getDescription());
    }

    @Override
    public void updateAlbumFromDto(AlbumDto albumDto, Album album) {
        album.setName(albumDto.getName());
    }

    @Override
    public void updateArtistFromDto(ArtistDto artistDto, Artist artist) {
        artist.setTitle(artistDto.getTitle());
    }
}
