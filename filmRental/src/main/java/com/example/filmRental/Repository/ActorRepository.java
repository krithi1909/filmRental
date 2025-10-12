package com.example.filmRental.Repository;

import com.example.filmRental.Entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByFirstNameContaining(String firstName);
    List<Actor> findByLastNameContaining(String lastName);

    @Query(value = "SELECT a.* FROM actor a " +
            "JOIN film_actor fa ON a.actor_id = fa.actor_id " +
            "GROUP BY a.actor_id " +
            "ORDER BY COUNT(fa.film_id) DESC " +
            "LIMIT 10", nativeQuery = true)
List<Actor> findTopTenActorsByFilmCount();
}