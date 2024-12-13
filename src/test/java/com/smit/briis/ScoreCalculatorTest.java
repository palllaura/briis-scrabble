package com.smit.briis;

import com.smit.briis.service.ScoreCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
public class ScoreCalculatorTest {

    @Autowired
    private ScoreCalculator calculator;

    @Test
    void calculateScoreNormalWord() {
        int score = calculator.calculateWordScore("smit");
        Assertions.assertEquals(score, 6);
    }

    @Test
    void calculateScoreEmptyWord() {
        int score = calculator.calculateWordScore("");
        Assertions.assertEquals(score, 0);
    }

    @Test
    void calculateScoreForeignCharacters() {
        int score = calculator.calculateWordScore("õäöü1234");
        Assertions.assertEquals(score, 0);
    }
}
