package com.books.onlinebook.service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

public interface MediaPostService {
	
	public List<String> getMediaPost(String isbn);
	
}
