package com.smit.briis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScoreCalculator {

    private static final Logger logger = LoggerFactory.getLogger(ScoreCalculator.class);

    public int calculateWordScore(String word) {
        if (word == null || word.isEmpty()) {
            logger.info("Empty input: {}", word);
            return 0;
        }

        char[] letters = word.toCharArray();
        int score = 0;
        for (char letter : letters) {
            score += getScore(letter);
        }
        logger.info("Calculated score for word '{}'. Score: {}", word.toUpperCase(), score);
        return score;
    }

    private int getScore(char letter) {
        return switch (letter) {
            case 'e', 'a', 'i', 'o', 'n', 'r', 't', 'l', 's', 'u' -> 1;
            case 'd', 'g' -> 2;
            case 'b', 'c', 'm', 'p' -> 3;
            case 'f', 'h', 'v', 'w', 'y' -> 4;
            case 'k' -> 5;
            case 'j', 'x' -> 8;
            case 'q', 'z' -> 10;
            default -> 0;
        };
    }
}
