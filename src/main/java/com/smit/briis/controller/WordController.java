package com.smit.briis.controller;

import com.smit.briis.service.ScoreCalculator;
import com.smit.briis.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/words")
public class WordController {

    private final WordService service;
    private final ScoreCalculator calculator;

    public WordController(WordService service, ScoreCalculator calculator) {
        this.service = service;
        this.calculator = calculator;
    }

    @GetMapping("/check/{word}")
    public ResponseEntity<?> checkWord(@PathVariable String word) {
        word = word.toLowerCase().trim();
        boolean isValid = service.validateIfWordIsCorrect(word);

        if (!isValid) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Invalid word submitted."
            ));
        }

        int score = 0;
        boolean inLibrary = service.checkIfWordInLibrary(word);
        if (inLibrary) {
            score = calculator.calculateWordScore(word);
        }
        String displayWord = word.toUpperCase();

        return ResponseEntity.ok(Map.of(
                "inLibrary", inLibrary,
                "score", score,
                "word", displayWord
        ));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addWord(@RequestBody String word) {
        word = word.toLowerCase().trim();

        if (service.saveWordIfNotExists(word)) {
            return ResponseEntity.ok(Map.of(
                    "message", "Word added successfully!"
            ));
        } else {
            return ResponseEntity.badRequest().body(Map.of(
                    "message", "Failed to save word."
            ));
        }
    }
}

