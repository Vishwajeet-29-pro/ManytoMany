package com.manytomany.musiclibrary.album;

import java.util.List;

public class AlbumDto {
    private int id;
    private String name;
    private String genre;
    private String description;

    public AlbumDto(int id, String name, String genre, String description) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description= description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre){
        this.genre= genre;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }
}
