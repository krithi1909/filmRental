package com.example.filmRental;


import com.example.filmRental.Controller.ActorController;
import com.example.filmRental.Dto.ActorDto;
import com.example.filmRental.Entity.Actor;
import com.example.filmRental.Entity.Film;
import com.example.filmRental.Service.ActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActorControllerTest {

    @InjectMocks
    private ActorController actorController;

    @Mock
    private ActorService actorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewActor() {
        ActorDto dto = new ActorDto("John", "Doe");
        doNothing().when(actorService).addActor(dto);

        ResponseEntity<String> response = actorController.addNewActor(dto);
        assertEquals("Record Created Successfully", response.getBody());
    }

    @Test
    void testGetActorsByLastName() {
        when(actorService.getActorsByLastName("Doe")).thenReturn(List.of(new Actor()));
        ResponseEntity<List<Actor>> response = actorController.getActorsByLastName("Doe");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testGetActorsByFirstName() {
        when(actorService.getActorsByFirstName("John")).thenReturn(List.of(new Actor()));
        ResponseEntity<List<Actor>> response = actorController.getActorsByFirstName("John");
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testUpdateLastName() {
        Actor actor = new Actor();
        when(actorService.updateLastName(1L, "Smith")).thenReturn(actor);
        ResponseEntity<Actor> response = actorController.updateLastName(1L, "Smith");
        assertEquals(actor, response.getBody());
    }

    @Test
    void testUpdateFirstName() {
        Actor actor = new Actor();
        when(actorService.updateFirstName(1L, "Jane")).thenReturn(actor);
        ResponseEntity<Actor> response = actorController.updateFirstName(1L, "Jane");
        assertEquals(actor, response.getBody());
    }

    @Test
    void testGetFilmsByActorId() {
        when(actorService.getFilmsByActorId(1L)).thenReturn(List.of(new Film()));
        ResponseEntity<List<Film>> response = actorController.getFilmsByActorId(1L);
        assertEquals(1, response.getBody().size());
    }

    @Test
    void testAssignFilmToActor() {
        doNothing().when(actorService).assignFilmToActor(1L, 100L);
        ResponseEntity<String> response = actorController.assignFilmToActor(1L, 100L);
        assertEquals("Film assigned to actor ID: 1", response.getBody());
    }

    @Test
    void testGetTopTenActorsByFilmCount() {
        when(actorService.getTopTenActorsByFilmCount()).thenReturn(List.of(new Actor()));
        ResponseEntity<List<Actor>> response = actorController.getTopTenActorsByFilmCount();
        assertEquals(1, response.getBody().size());
    }
}
