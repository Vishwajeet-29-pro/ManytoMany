package com.manytomany.musiclibrary.album;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manytomany.musiclibrary.artist.Artist;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="album")
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="album_id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="genre")
	private String genre;
	@Column(name="description")
	private String description;
	
	@ManyToMany(mappedBy="albums", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<Artist> artists = new HashSet<>();
	
	public Album() {

	}

	public Album(int id, String name, String genre, String description){
		this.id=id;
		this.name=name;
		this.genre=genre;
		this.description=description;
	}

	public Album(int id, String name, String genre, String description, Set<Artist> artists) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.description = description;
		this.artists= artists;
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


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Set<Artist> getArtist() {
		return artists;
	}


	public void setArtist(Set<Artist> artists) {
		this.artists = artists;
	}
	
	
}
