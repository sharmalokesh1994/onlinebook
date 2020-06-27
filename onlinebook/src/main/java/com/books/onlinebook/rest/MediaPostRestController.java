package com.books.onlinebook.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.onlinebook.service.MediaPostService;

@RestController
@RequestMapping("/onlinebook/api/mediapost")
public class MediaPostRestController {

	@Autowired
	private MediaPostService mediaPostService;
	
	@GetMapping("/isbn/{isbn}")
	public List<String> getMediaPost(@PathVariable String isbn) {
		
		List<String> posts = mediaPostService.getMediaPost(isbn);
		
		return posts;
		
	}
}
