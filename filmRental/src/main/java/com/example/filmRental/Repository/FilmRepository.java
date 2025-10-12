
package com.example.filmRental.Repository;

import com.example.filmRental.Entity.Film;
import com.example.filmRental.Entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findByTitleContaining(String title);
    List<Film> findByReleaseYear(Integer releaseYear);
    List<Film> findByRentalDurationGreaterThan(Byte duration);
    List<Film> findByRentalDurationLessThan(Byte duration);
    List<Film> findByRentalRateGreaterThan(BigDecimal rate);
    List<Film> findByRentalRateLessThan(BigDecimal rate);
    List<Film> findByLengthGreaterThan(Short length);
    List<Film> findByLengthLessThan(Short length);
    List<Film> findByRatingGreaterThan(String rating);
    List<Film> findByRatingLessThan(String rating);
    List<Film> findByLanguage_Name(String language);
    List<Film> findByReleaseYearBetween(Integer from, Integer to);

    @Query(value = "SELECT f.* FROM film f JOIN film_actor fa ON f.film_id = fa.film_id WHERE fa.actor_id = :actorId", nativeQuery = true)
    List<Film> findFilmsByActorId(@Param("actorId") Long actorId);

    @Query(value = "SELECT release_year, COUNT(*) FROM film GROUP BY release_year", nativeQuery = true)
    List<Object[]> countFilmsByYear();

    @Query(value = "SELECT a.* FROM actor a JOIN film_actor fa ON a.actor_id = fa.actor_id WHERE fa.film_id = :filmId", nativeQuery = true)
    List<Actor> findActorsByFilmId(@Param("filmId") Long filmId);

    @Query(value = "SELECT f.* FROM film f JOIN film_category fc ON f.film_id = fc.film_id JOIN category c ON fc.category_id = c.category_id WHERE c.name = :category", nativeQuery = true)
    List<Film> findFilmsByCategory(@Param("category") String category);
}
