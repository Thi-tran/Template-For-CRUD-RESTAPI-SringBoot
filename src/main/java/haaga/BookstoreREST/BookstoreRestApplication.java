package haaga.BookstoreREST;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haaga.BookstoreREST.domain.Book;
import haaga.BookstoreREST.domain.BookRepository;

@SpringBootApplication
public class BookstoreRestApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookstoreRestApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository) {
		return (args) -> {
			repository.save(new Book("The Dawn"));
			repository.save(new Book("The Key"));
		};
	}
}
