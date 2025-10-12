package com.example.filmRental.Controller;

import com.example.filmRental.Dto.ActorDto;
import com.example.filmRental.Entity.Actor;
import com.example.filmRental.Entity.Film;
import com.example.filmRental.Exception.CustomValidationException;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @PostMapping("/post")
    public ResponseEntity<String> addNewActor(@RequestBody ActorDto dto) {
        if (dto.getFirstName() == null || dto.getLastName() == null) {
            throw new CustomValidationException("First name and last name are required.");
        }
        actorService.addActor(dto);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<Actor>> getActorsByLastName(@PathVariable String ln) {
        List<Actor> actors = actorService.getActorsByLastName(ln);
        if (actors.isEmpty()) throw new ResourceNotFoundException("No actors found with last name: " + ln);
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<Actor>> getActorsByFirstName(@PathVariable String fn) {
        List<Actor> actors = actorService.getActorsByFirstName(fn);
        if (actors.isEmpty()) throw new ResourceNotFoundException("No actors found with first name: " + fn);
        return ResponseEntity.ok(actors);
    }

    @PutMapping("/update/lastname/{id}")
    public ResponseEntity<Actor> updateLastName(@PathVariable Long id, @RequestParam String lastName) {
        return ResponseEntity.ok(actorService.updateLastName(id, lastName));
    }

    @PutMapping("/update/firstname/{id}")
    public ResponseEntity<Actor> updateFirstName(@PathVariable Long id, @RequestParam String firstName) {
        return ResponseEntity.ok(actorService.updateFirstName(id, firstName));
    }

    @GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> getFilmsByActorId(@PathVariable Long id) {
        List<Film> films = actorService.getFilmsByActorId(id);
        if (films.isEmpty()) throw new ResourceNotFoundException("No films found for actor ID: " + id);
        return ResponseEntity.ok(films);
    }

    @PutMapping("/{id}/film")
    public ResponseEntity<String> assignFilmToActor(@PathVariable Long id, @RequestParam Long filmId) {
        actorService.assignFilmToActor(id, filmId);
        return ResponseEntity.ok("Film assigned to actor ID: " + id);
    }

    @GetMapping("/toptenbyfilmcount")
    public ResponseEntity<List<Actor>> getTopTenActorsByFilmCount() {
        return ResponseEntity.ok(actorService.getTopTenActorsByFilmCount());
    }
}