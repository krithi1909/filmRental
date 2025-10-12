package com.example.filmRental.Service;


import com.example.filmRental.Entity.Language;
import com.example.filmRental.Repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import java.util.List;
 
@Service
public class LanguageService {
 
    @Autowired
    private LanguageRepository languageRepository;
 
    // Get all languages
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
 
    // Get language by ID
    public Language getLanguageById(Byte id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found with ID: " + id));
    }
 
    // Get language by name
    public Language getLanguageByName(String name) {
        return languageRepository.findByName(name);
    }
 
    // Add a new language
    public Language addLanguage(Language language) {
        return languageRepository.save(language);
    }
 
    // Update an existing language
    public Language updateLanguage(Byte id, Language updatedLanguage) {
        Language existing = getLanguageById(id);
        existing.setName(updatedLanguage.getName());
        existing.setLastUpdate(updatedLanguage.getLastUpdate());
        return languageRepository.save(existing);
    }
 
    // Delete a language
    public void deleteLanguage(Byte id) {
        languageRepository.deleteById(id);
    }
}