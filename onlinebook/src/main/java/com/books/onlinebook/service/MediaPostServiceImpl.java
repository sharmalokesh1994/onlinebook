package com.books.onlinebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.onlinebook.dao.BookStoreDAO;
import com.books.onlinebook.entity.Book;
import com.books.onlinebook.media.MediaSearchSingleton;
import com.books.onlinebook.rest.exception.ObjNotFoundException;

@Service
public class MediaPostServiceImpl implements MediaPostService{

	@Autowired
	private BookStoreDAO bookStoreDAO;
	
	MediaSearchSingleton  mediaSearchSingleton = MediaSearchSingleton.getInstance();
	
	@Override
	@Transactional
	public List<String> getMediaPost(String isbn) {
		// TODO Auto-generated method stub
		
		Book book = (Book) bookStoreDAO.getBook(Book.class, isbn);
		
		if(book==null) {
			throw new ObjNotFoundException("Book Not found");
		}
		
		List<String> posts = mediaSearchSingleton.getPosts(book.getTitle());
		
		if(posts.size()==0){
			posts.add("No Posts for this Book");
		}
		
		return posts;
	}

}
