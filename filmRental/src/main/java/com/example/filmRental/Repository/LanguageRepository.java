package com.example.filmRental.Repository;
 
 
 
import com.example.filmRental.Entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface LanguageRepository extends JpaRepository<Language, Byte> {
    Language findByName(String name);
}
 
