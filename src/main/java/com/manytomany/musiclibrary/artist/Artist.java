package com.manytomany.musiclibrary.artist;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manytomany.musiclibrary.album.Album;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="artist")
public class Artist {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name="artist_id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="release_date")
	private Date releaseDate;
	@Column(name="cover_art")
	private String coverArt;
	@Column(name="description")
	private String description;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(
			name = "artist_album",
			joinColumns = @JoinColumn(name="artist_id"),
			inverseJoinColumns = @JoinColumn(name="album_id")
			)
	//@JsonIgnore
	Set<Album> albums = new HashSet<>();
	
	public Artist() {
		
	}
	public Artist(int id, String title, Date releaseDate, String coverArt, String description){
		this.id=id;
		this.title=title;
		this.releaseDate=releaseDate;
		this.coverArt=coverArt;
		this.description=description;
	}

	public Artist(int id, String title, Date releaseDate, String coverArt, String description, Set<Album> albums) {
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.coverArt = coverArt;
		this.description = description;
		this.albums = albums;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCoverArt() {
		return coverArt;
	}

	public void setCoverArt(String coverArt) {
		this.coverArt = coverArt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Album> getAlbum() {
		return albums;
	}

	public void setAlbum(Set<Album> albums) {
		this.albums = albums;
	}
	
	
}
