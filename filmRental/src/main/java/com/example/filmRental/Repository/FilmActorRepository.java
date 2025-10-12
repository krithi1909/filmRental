package com.example.filmRental.Repository;


import com.example.filmRental.Entity.FilmActor;
import com.example.filmRental.Entity.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId> {
    List<FilmActor> findByActorId(Long actorId);
    List<FilmActor> findByFilmId(Long filmId);
}
