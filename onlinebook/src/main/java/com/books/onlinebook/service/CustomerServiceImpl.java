package com.books.onlinebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.onlinebook.dao.BookStoreDAO;
import com.books.onlinebook.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private BookStoreDAO bookStoreDAO;

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		bookStoreDAO.saveObject(theCustomer);
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return bookStoreDAO.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		bookStoreDAO.deleteCustomer(theId);
	}
}





