package com.smit.briis.repository;

import com.smit.briis.entity.Word;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository {
    Boolean existsByValue(String value);

    Word save(Word word);
}
