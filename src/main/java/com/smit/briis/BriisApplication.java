package com.smit.briis;

import com.smit.briis.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.nio.file.Files;
import java.util.stream.Stream;

@SpringBootApplication
public class BriisApplication {

	private static final Logger logger = LoggerFactory.getLogger(BriisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BriisApplication.class, args);
		logger.info("BriisApplication started successfully.");
	}

	@Bean
	CommandLineRunner initDatabase(WordService wordService) {
		return args -> {
			try (Stream<String> lines = Files.lines(new ClassPathResource("data/dictionary.txt").getFile().toPath())) {
				lines.forEach(wordService::saveWordIfNotExists);
			} catch (Exception e) {
				logger.error("Error reading dictionary: {}", e.getMessage(), e);
			}
		};
	}
}
