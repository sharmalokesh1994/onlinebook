package com.books.onlinebook.media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.books.onlinebook.constant.ApplicationConstants;

public class MediaSearchSingleton {
	private static MediaSearchSingleton instance;
	
	private List<MediaSearch> posts;
	
	private MediaSearchSingleton(){
		
		RestTemplate restTemplate = new RestTemplate();
		posts = Arrays.asList(restTemplate.getForObject(ApplicationConstants.MEDIA_URL,MediaSearch[].class));
	}
	
	
	public static MediaSearchSingleton getInstance(){
		
		if( instance==null ){
			synchronized(MediaSearchSingleton.class){
				if(instance==null){
					instance = new MediaSearchSingleton();
				}
			}
		}
		
		return instance;
	}
	
	public List<String> getPosts(String title){
		List<String> list = new ArrayList<>();
		
		posts.parallelStream().forEach(m->{
			if( m.getTitle().toLowerCase().contains(title.toLowerCase()) || 
					m.getBody().toLowerCase().contains(title.toLowerCase()) ){
				list.add(m.getTitle());
			}
		});
		
		return list;
	}
	
}
