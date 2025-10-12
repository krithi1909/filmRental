package com.example.filmRental.Entity;

import java.io.Serializable;
import java.util.Objects;

public class FilmActorId implements Serializable {
    private Long filmId;
    private Long actorId;

    public FilmActorId() {}

    public FilmActorId(Long filmId2, Long actorId2) {
        this.filmId = filmId2;
        this.actorId = actorId2;
    }

    // Getters and Setters
    public Long getFilmId() { return filmId; }
    public void setFilmId(Long filmId) { this.filmId = filmId; }

    public Long getActorId() { return actorId; }
    public void setActorId(Long actorId) { this.actorId = actorId; }

    // equals() and hashCode() are required for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilmActorId)) return false;
        FilmActorId that = (FilmActorId) o;
        return Objects.equals(filmId, that.filmId) &&
               Objects.equals(actorId, that.actorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, actorId);
    }
}