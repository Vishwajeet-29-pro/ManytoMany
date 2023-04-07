package com.manytomany.musiclibrary.artist;

import java.sql.Date;
import java.util.List;

public class ArtistDto {
    private int id;
    private String title;
    private String coverArt;
    private Date releaseDate;
    private String description;


    public ArtistDto(int id, String title, Date releaseDate, String coverArt, String description){
        this.id = id;
        this.title = title;
        this.releaseDate=releaseDate;
        this.coverArt=coverArt;
        this.description=description;
    }

    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setTitle(String title){
        this.title=title;
    }

    public String getCoverArt() {
        return coverArt;
    }

    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
