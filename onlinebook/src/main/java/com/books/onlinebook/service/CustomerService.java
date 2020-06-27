package com.books.onlinebook.service;

import java.util.List;

import com.books.onlinebook.entity.Customer;

public interface CustomerService {

	

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
