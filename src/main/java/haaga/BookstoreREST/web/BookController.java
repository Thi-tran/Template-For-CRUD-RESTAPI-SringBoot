package haaga.BookstoreREST.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import haaga.BookstoreREST.domain.Book;
import haaga.BookstoreREST.domain.BookRepository;

@Controller
public class BookController {
	
	@Autowired
	private BookRepository repository;
	
	// Restful to get all students
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> booklistRest() {
		return (List<Book>) repository.findAll();
	}
	
	// Read Book
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId){
		return repository.findById(bookId);
	}
	
	// Create Book
	@RequestMapping(value="/addbook", method = RequestMethod.POST)
	public @ResponseBody List<Book> createBook(@RequestBody Map<String, String> body) {
		String name = body.get("name");
		Book newBook = new Book(name);
		repository.save(newBook);
		return (List<Book>) repository.findAll();
	}
	
	// Edit Book
	@RequestMapping(value="/edit/{bookId}", method= RequestMethod.PUT) 
	public @ResponseBody Optional<Book> updateBook(@PathVariable("bookId") Long bookId, @RequestBody Map<String,String> body){
		
		String name = body.get("name");
		Book oldBook = repository.findById(bookId).get();
		oldBook.setName(name);
		repository.save(oldBook);
		
		return repository.findById(bookId);
	}
	
	// Delete Book
	@RequestMapping(value="/delete/{bookId}", method= RequestMethod.DELETE) 
	public @ResponseBody List<Book> deleteBook(@PathVariable("bookId") Long bookId, @RequestBody Map<String,String> body){
		
		repository.deleteById(bookId);
		return (List<Book>) repository.findAll();
	}

}
