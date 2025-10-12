package com.example.filmRental;

import com.example.filmRental.Entity.Film;
import com.example.filmRental.Controller.FilmController;
import com.example.filmRental.Entity.Actor;
import com.example.filmRental.Service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FilmControllerTest {

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFilm() {
        Film film = new Film();
        film.setTitle("Inception");

        when(filmService.saveFilm(film)).thenReturn(film); // assuming saveFilm returns Film

        ResponseEntity<String> response = filmController.addFilm(film);
        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    void testGetFilmsByTitle() {
        when(filmService.getFilmsByTitle("Inception")).thenReturn(List.of(new Film()));
        ResponseEntity<List<Film>> response = filmController.getFilmsByTitle("Inception");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetFilmsByYear() {
        when(filmService.getFilmsByReleaseYear(2010)).thenReturn(List.of(new Film()));
        ResponseEntity<List<Film>> response = filmController.getFilmsByYear(2010);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetFilmsByRentalDurationGreaterThan() {
        when(filmService.getFilmsByRentalDurationGreaterThan((byte) 5)).thenReturn(List.of(new Film()));
        ResponseEntity<List<Film>> response = filmController.getFilmsByRentalDurationGreaterThan((byte) 5);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testAssignActorToFilm() {
        when(filmService.assignActorToFilm(1L, 100L)).thenReturn("Actor assigned");
        ResponseEntity<String> response = filmController.assignActorToFilm(1L, 100L);
        assertEquals("Actor assigned", response.getBody());
    }

    @Test
    void testUpdateFilmTitle() {
        Film film = new Film();
        film.setTitle("New Title");
        when(filmService.updateFilmTitle(1L, "New Title")).thenReturn(film);
        ResponseEntity<Film> response = filmController.updateFilmTitle(1L, film);
        assertEquals("New Title", response.getBody().getTitle());
    }

    @Test
    void testGetActorsByFilmId() {
        when(filmService.getActorsByFilmId(1L)).thenReturn(List.of(new Actor()));
        ResponseEntity<List<Actor>> response = filmController.getActorsByFilmId(1L);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testCountFilmsByYear() {
        Object[] row = new Object[] {2010, 5};
        List<Object[]> mockResult = new ArrayList<>();
        mockResult.add(row);

        when(filmService.countFilmsByYear()).thenReturn(mockResult);

        ResponseEntity<List<Object[]>> response = filmController.countFilmsByYear();
        assertEquals(1, response.getBody().size());
        assertArrayEquals(row, response.getBody().get(0));
    }

    // Add similar tests for other endpoints as needed
}
