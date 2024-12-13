package com.smit.briis.service;

import com.smit.briis.entity.Word;
import com.smit.briis.repository.WordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WordService {

    private static final Logger logger = LoggerFactory.getLogger(WordService.class);

    private final WordRepository repository;

    public WordService(@Qualifier("wordRepositoryImpl") WordRepository repository) {
        this.repository = repository;
    }

    public boolean validateIfWordIsCorrect(String word) {
        return word.matches("^[a-zõäöü]+$");
    }

    public boolean checkIfWordInLibrary(String word) {
        return repository.existsByValue(word);
    }

    public boolean saveWordIfNotExists(String word) {
        word = word.toLowerCase().trim();

        if (!this.validateIfWordIsCorrect(word)) {
            logger.debug("Invalid word: {}", word);
            return false;
        }

        if (this.checkIfWordInLibrary(word)) {
            logger.debug("Word already in database: {}", word);
            return false;
        }

        repository.save(new Word(word));
        logger.info("Saved word to database: {}", word);
        return true;
    }
}
