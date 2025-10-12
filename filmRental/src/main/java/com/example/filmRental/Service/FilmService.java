
package com.example.filmRental.Service;

import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.Actor;
import com.example.filmRental.Entity.Language;
import com.example.filmRental.Repository.FilmRepository;
import com.example.filmRental.Repository.ActorRepository;
import com.example.filmRental.Repository.LanguageRepository;
import com.example.filmRental.Entity.FilmActor;
import com.example.filmRental.Repository.FilmActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private FilmActorRepository filmActorRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public List<Film> getFilmsByTitle(String title) {
        return filmRepository.findByTitleContaining(title);
    }

    public List<Film> getFilmsByReleaseYear(Integer year) {
        return filmRepository.findByReleaseYear(year);
    }

    public List<Film> getFilmsByReleaseYearBetween(Integer from, Integer to) {
        return filmRepository.findByReleaseYearBetween(from, to);
    }

    public List<Film> getFilmsByRentalDurationGreaterThan(Byte duration) {
        return filmRepository.findByRentalDurationGreaterThan(duration);
    }

    public List<Film> getFilmsByRentalDurationLessThan(Byte duration) {
        return filmRepository.findByRentalDurationLessThan(duration);
    }

    public List<Film> getFilmsByRentalRateGreaterThan(BigDecimal rate) {
        return filmRepository.findByRentalRateGreaterThan(rate);
    }

    public List<Film> getFilmsByRentalRateLessThan(BigDecimal rate) {
        return filmRepository.findByRentalRateLessThan(rate);
    }

    public List<Film> getFilmsByLengthGreaterThan(Short length) {
        return filmRepository.findByLengthGreaterThan(length);
    }

    public List<Film> getFilmsByLengthLessThan(Short length) {
        return filmRepository.findByLengthLessThan(length);
    }

    public List<Film> getFilmsByRatingGreaterThan(String rating) {
        return filmRepository.findByRatingGreaterThan(rating);
    }

    public List<Film> getFilmsByRatingLessThan(String rating) {
        return filmRepository.findByRatingLessThan(rating);
    }

    public List<Film> getFilmsByLanguage(String language) {
        return filmRepository.findByLanguage_Name(language);
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    public Film updateFilmTitle(Long id, String title) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        film.setTitle(title);
        return filmRepository.save(film);
    }

    public Film updateFilmReleaseYear(Long id, Integer year) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        film.setReleaseYear(year);
        return filmRepository.save(film);
    }

    public Film updateRentalDuration(Long id, Byte duration) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        film.setRentalDuration(duration);
        return filmRepository.save(film);
    }

    public Film updateRentalRate(Long id, BigDecimal rate) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        film.setRentalRate(rate);
        return filmRepository.save(film);
    }

    public Film updateRating(Long id, String rating) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        film.setRating(rating);
        return filmRepository.save(film);
    }

    public Film updateLanguage(Long id, String languageName) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new RuntimeException("Film not found"));
        Language language = languageRepository.findByName(languageName);
        film.setLanguage(language);
        return filmRepository.save(film);
    }

    public List<Object[]> countFilmsByYear() {
        return filmRepository.countFilmsByYear();
    }

    public List<Actor> getActorsByFilmId(Long filmId) {
        return filmRepository.findActorsByFilmId(filmId);
    }

    public List<Film> getFilmsByCategory(String category) {
        return filmRepository.findFilmsByCategory(category);
    }

    public String assignActorToFilm(Long filmId, Long actorId) {
        FilmActor fa = new FilmActor(filmId, actorId, new Timestamp(System.currentTimeMillis()));
        filmActorRepository.save(fa);
        return "Actor assigned to film successfully";
    }
}
