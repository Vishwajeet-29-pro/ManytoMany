package com.manytomany.musiclibrary.album;

import com.manytomany.musiclibrary.artist.Artist;
import com.manytomany.musiclibrary.artist.ArtistDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    AlbumDto toDto(Album album);
    ArtistDto toDto(Artist artist);

    @Mapping(target = "artists",ignore = true)
    Album toEntity(AlbumDto albumDto);

    @Mapping(target = "albums",ignore = true)
    Artist toEntity(ArtistDto artistDto);

    void updateAlbumFromDto(AlbumDto albumDto, @MappingTarget Album album);

    void updateArtistFromDto(ArtistDto artistDto, @MappingTarget Artist artist);
}
