package com.example.filmRental.Service;

import com.example.filmRental.Dto.ActorDto;
import com.example.filmRental.Entity.Actor;
import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.FilmActor;
import com.example.filmRental.Entity.FilmActorId;
import com.example.filmRental.Repository.ActorRepository;
import com.example.filmRental.Repository.FilmActorRepository;
import com.example.filmRental.Repository.FilmRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;

    public void addActor(ActorDto dto) {
        Actor actor = new Actor();
        actor.setFirstName(dto.getFirstName());
        actor.setLastName(dto.getLastName());
        actor.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        actorRepository.save(actor);
    }

    public List<Actor> getActorsByFirstName(String firstName) {
        return actorRepository.findByFirstNameContaining(firstName);
    }

    public List<Actor> getActorsByLastName(String lastName) {
        return actorRepository.findByLastNameContaining(lastName);
    }

    public Actor updateFirstName(Long id, String firstName) {
        Actor actor = actorRepository.findById(id).orElseThrow();
        actor.setFirstName(firstName);
        actor.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        return actorRepository.save(actor);
    }

    public Actor updateLastName(Long id, String lastName) {
        Actor actor = actorRepository.findById(id).orElseThrow();
        actor.setLastName(lastName);
        actor.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        return actorRepository.save(actor);
    }

    public List<Film> getFilmsByActorId(Long actorId) {
        List<FilmActor> filmActors = filmActorRepository.findByActorId(actorId);
        return filmActors.stream()
                .map(fa -> filmRepository.findById(fa.getFilmId()).orElse(null))
                .filter(film -> film != null)
                .collect(Collectors.toList());
    }

    @Transactional
    public void assignFilmToActor(Long actorId, Long filmId) {
        FilmActorId id = new FilmActorId(filmId, actorId);
        if (!filmActorRepository.existsById(id)) {
            FilmActor filmActor = new FilmActor(filmId, actorId, Timestamp.valueOf(LocalDateTime.now()));
            filmActorRepository.save(filmActor);
        }
    }

    public List<Actor> getTopTenActorsByFilmCount() {
        return actorRepository.findTopTenActorsByFilmCount();
    }
}