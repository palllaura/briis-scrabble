package com.smit.briis.repository;

import com.smit.briis.entity.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepositoryImpl extends WordRepository, CrudRepository<Word, Integer> { }
