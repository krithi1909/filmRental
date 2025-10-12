package com.example.filmRental.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "film_actor")
@IdClass(FilmActorId.class)

public class FilmActor {

    @Id
    @Column(name = "film_id")
    private Long filmId;

    @Id
    @Column(name = "actor_id")
    private Long actorId;

    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

	public Long getFilmId() {
		return filmId;
	}

	public void setFilmId(Long filmId) {
		this.filmId = filmId;
	}

	public Long getActorId() {
		return actorId;
	}

	public void setActorId(Long actorId) {
		this.actorId = actorId;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public FilmActor(Long filmId, long actorId, Timestamp lastUpdate) {
		super();
		this.filmId = filmId;
		this.actorId = actorId;
		this.lastUpdate = lastUpdate;
	}

	public FilmActor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    
}