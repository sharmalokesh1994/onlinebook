package com.books.onlinebook.dao;

import java.util.List;

import com.books.onlinebook.entity.Book;
import com.books.onlinebook.entity.Customer;

public interface BookStoreDAO {


	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
	public Object getObject(Class classObject,int theId);
	
	public Object getBook(Class classObject,String isbn);
	
	public void saveObject(Object object);
	public List getObjects(Class className,String propertyName, String propertyValue);

	int buyBook(String isbn);

	int increaseBook(Book book);

	void deleteObject(Object obj);
	
}
