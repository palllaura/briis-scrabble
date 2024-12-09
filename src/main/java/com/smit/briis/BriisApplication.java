package com.smit.briis;

import com.smit.briis.entity.Word;
import com.smit.briis.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.util.stream.Stream;

@SpringBootApplication
public class BriisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BriisApplication.class, args);
	}


	@Bean
	CommandLineRunner initDatabase(WordRepository repository) {
		return args -> {
			try (Stream<String> lines = Files.lines(new ClassPathResource("data/dictionary.txt").getFile().toPath())) {
				lines.forEach(word -> {
					try {
						repository.save(new Word(word.trim()));
						System.out.println("Saved word: " + word.trim());
					} catch (Exception e) {
						System.err.println("Failed to save word: " + word.trim() + " - " + e.getMessage());
					}
				});
			} catch (Exception e) {
				System.err.println("Error reading word collection: " + e.getMessage());
			}
		};
	}
}


