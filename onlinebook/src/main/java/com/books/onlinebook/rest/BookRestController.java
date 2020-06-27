package com.books.onlinebook.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.onlinebook.entity.Book;
import com.books.onlinebook.entity.Order;
import com.books.onlinebook.rest.exception.ObjNotFoundException;
import com.books.onlinebook.service.BookService;

@RestController
@RequestMapping("/onlinebook/api/book")
public class BookRestController {
	
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/isbn/{isbn}")
	public Book getBookByIsbn(@PathVariable String isbn) {
		
		Book book = bookService.getBookByIsbn(isbn);
		
		if(book==null) {
			throw new ObjNotFoundException("Book Not found");
		}
		
		return book;
		
	}
	
	@GetMapping("/title/{title}")
	public List<Book> getBookByTitle(@PathVariable String title) {
		// can add Exception here as well 
		return bookService.getBookByTitle(title);
		
	}
	
	@GetMapping("/author/{author}")
	public List<Book> getBookByAuther(@PathVariable String author) {
		//can add Exception here as well
		
		return bookService.getBookByAuther(author);
		
	}
	
	@PostMapping("/add")
	public Book addBook(@RequestBody Book book  ) {
		//customerService.saveCustomer(theCustomer);
		bookService.saveBooks(book);
		
		return book;
	}
	
	// for update
	@PutMapping("/increasecount")
	public Book increaseBook(@RequestBody Book book  ) {
		bookService.increaseBook(book);
		return book;
		
	}
	
	
	@PostMapping("/buy")
	public Order orderBook(@RequestBody Order order  ) {
		// We have to set it null so hibernate can create new id
		order.setId(0);
		bookService.makeOrder(order);
		
		return order;
		
	}
	
	
	@DeleteMapping("/remove/{isbn}")
	public String removeBooks(@PathVariable String isbn) {
		
		Book book = bookService.getBookByIsbn(isbn);
		if(book==null){
			throw new ObjNotFoundException("Book not found");
		}
		
		return book.toString()+" removed";
		
	}
	
	

}
