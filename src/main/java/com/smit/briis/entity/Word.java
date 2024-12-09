package com.smit.briis.entity;

import jakarta.persistence.*;

@Entity
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word_value", unique = true, nullable = false)
    private String value;

    public Word(String value) {
        this.value = value;
    }

    public Word() {

    }
}

