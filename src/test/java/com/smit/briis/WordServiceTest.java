package com.smit.briis;

import com.smit.briis.repository.WordRepository;
import com.smit.briis.service.WordService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@SpringBootTest
public class WordServiceTest {

    @Test
    void checkIfWordInLibrary() {
        WordRepository repository = mock(WordRepository.class);
        WordService service = new WordService(repository);

        when(repository.existsByValue("cat")).thenReturn(true);

        assert (service.checkIfWordInLibrary("cat"));
        assert (!service.checkIfWordInLibrary("dog"));
    }

    @Test
    void validateIfWordIsCorrect() {
        WordService service = new WordService(mock(WordRepository.class));

        assert (service.validateIfWordIsCorrect("cat"));
        assert (!service.validateIfWordIsCorrect("???"));
    }

    @Test
    void saveWordIfNotExists() {
        WordRepository repository = mock(WordRepository.class);
        WordService service = new WordService(repository);

        when(repository.existsByValue("cat")).thenReturn(true);
        when(repository.existsByValue("dog")).thenReturn(false);

        assert (!service.saveWordIfNotExists("???"));
        assert (!service.saveWordIfNotExists("cat"));
        assert (service.saveWordIfNotExists("dog"));
    }
}
