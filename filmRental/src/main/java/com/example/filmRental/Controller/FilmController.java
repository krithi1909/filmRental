package com.example.filmRental.Controller;

import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.Actor;
import com.example.filmRental.Exception.CustomValidationException;
import com.example.filmRental.Exception.ResourceNotFoundException;
import com.example.filmRental.Service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @PostMapping("/post")
    public ResponseEntity<String> addFilm(@RequestBody Film film) {
        if (film.getTitle() == null || film.getTitle().isEmpty()) {
            throw new CustomValidationException("Film title must not be empty.");
        }
        filmService.saveFilm(film);
        return ResponseEntity.ok("Record Created Successfully");
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<Film>> getFilmsByTitle(@PathVariable String title) {
        List<Film> films = filmService.getFilmsByTitle(title);
        if (films.isEmpty()) throw new ResourceNotFoundException("No films found with title: " + title);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Film>> getFilmsByYear(@PathVariable Integer year) {
        return ResponseEntity.ok(filmService.getFilmsByReleaseYear(year));
    }

    @GetMapping("/duration/gt/{rd}")
    public ResponseEntity<List<Film>> getFilmsByRentalDurationGreaterThan(@PathVariable Byte rd) {
        return ResponseEntity.ok(filmService.getFilmsByRentalDurationGreaterThan(rd));
    }

    @GetMapping("/duration/lt/{rd}")
    public ResponseEntity<List<Film>> getFilmsByRentalDurationLessThan(@PathVariable Byte rd) {
        return ResponseEntity.ok(filmService.getFilmsByRentalDurationLessThan(rd));
    }

    @GetMapping("/rate/gt/{rate}")
    public ResponseEntity<List<Film>> getFilmsByRentalRateGreaterThan(@PathVariable BigDecimal rate) {
        return ResponseEntity.ok(filmService.getFilmsByRentalRateGreaterThan(rate));
    }

    @GetMapping("/rate/lt/{rate}")
    public ResponseEntity<List<Film>> getFilmsByRentalRateLessThan(@PathVariable BigDecimal rate) {
        return ResponseEntity.ok(filmService.getFilmsByRentalRateLessThan(rate));
    }

    @GetMapping("/length/gt/{length}")
    public ResponseEntity<List<Film>> getFilmsByLengthGreaterThan(@PathVariable Short length) {
        return ResponseEntity.ok(filmService.getFilmsByLengthGreaterThan(length));
    }

    @GetMapping("/length/lt/{length}")
    public ResponseEntity<List<Film>> getFilmsByLengthLessThan(@PathVariable Short length) {
        return ResponseEntity.ok(filmService.getFilmsByLengthLessThan(length));
    }

    @GetMapping("/rating/gt/{rating}")
    public ResponseEntity<List<Film>> getFilmsByRatingGreaterThan(@PathVariable String rating) {
        return ResponseEntity.ok(filmService.getFilmsByRatingGreaterThan(rating));
    }

    @GetMapping("/rating/lt/{rating}")
    public ResponseEntity<List<Film>> getFilmsByRatingLessThan(@PathVariable String rating) {
        return ResponseEntity.ok(filmService.getFilmsByRatingLessThan(rating));
    }

    @GetMapping("/language/{lang}")
    public ResponseEntity<List<Film>> getFilmsByLanguage(@PathVariable String lang) {
        return ResponseEntity.ok(filmService.getFilmsByLanguage(lang));
    }

    @GetMapping("/betweenyear/{from}/{to}")
    public ResponseEntity<List<Film>> getFilmsBetweenYears(@PathVariable Integer from, @PathVariable Integer to) {
        return ResponseEntity.ok(filmService.getFilmsByReleaseYearBetween(from, to));
    }

    @GetMapping("/countbyyear")
    public ResponseEntity<List<Object[]>> countFilmsByYear() {
        return ResponseEntity.ok(filmService.countFilmsByYear());
    }

    @GetMapping("/{id}/actors")
    public ResponseEntity<List<Actor>> getActorsByFilmId(@PathVariable Long id) {
        List<Actor> actors = filmService.getActorsByFilmId(id);
        if (actors.isEmpty()) throw new ResourceNotFoundException("No actors found for film ID: " + id);
        return ResponseEntity.ok(actors);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Film>> getFilmsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(filmService.getFilmsByCategory(category));
    }

    @PutMapping("/{id}/actor")
    public ResponseEntity<String> assignActorToFilm(@PathVariable Long id, @RequestParam Long actorId) {
        return ResponseEntity.ok(filmService.assignActorToFilm(id, actorId));
    }

    @PutMapping("/update/title/{id}")
    public ResponseEntity<Film> updateFilmTitle(@PathVariable Long id, @RequestBody Film film) {
        return ResponseEntity.ok(filmService.updateFilmTitle(id, film.getTitle()));
    }

    @PutMapping("/update/releaseyear/{id}")
    public ResponseEntity<Film> updateFilmReleaseYear(@PathVariable Long id, @RequestBody Film film) {
        return ResponseEntity.ok(filmService.updateFilmReleaseYear(id, film.getReleaseYear()));
    }

    @PutMapping("/update/rentalduration/{id}")
    public ResponseEntity<Film> updateRentalDuration(@PathVariable Long id, @RequestBody Film film) {
        return ResponseEntity.ok(filmService.updateRentalDuration(id, film.getRentalDuration()));
    }

    @PutMapping("/update/rentalrate/{id}")
    public ResponseEntity<Film> updateRentalRate(@PathVariable Long id, @RequestBody Film film) {
        return ResponseEntity.ok(filmService.updateRentalRate(id, film.getRentalRate()));
    }

    @PutMapping("/update/rating/{id}")
    public ResponseEntity<Film> updateRating(@PathVariable Long id, @RequestBody Film film) {
        return ResponseEntity.ok(filmService.updateRating(id, film.getRating()));
    }

    @PutMapping("/update/language/{id}")
    public ResponseEntity<Film> updateLanguage(@PathVariable Long id, @RequestParam String language) {
        return ResponseEntity.ok(filmService.updateLanguage(id, language));
    }
}