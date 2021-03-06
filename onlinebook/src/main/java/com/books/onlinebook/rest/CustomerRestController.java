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

import com.books.onlinebook.entity.Customer;
import com.books.onlinebook.rest.exception.ObjNotFoundException;
import com.books.onlinebook.service.CustomerService;

@RestController
@RequestMapping("/onlinebook/api")
public class CustomerRestController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	
	
	// add mapping for GET /customers
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		
		Customer cus = customerService.getCustomer(customerId);
		
		if(cus==null) {
			throw new ObjNotFoundException("Customer Not found");
		}
		
		return cus;
		
	}
	
	
	@PostMapping("/customers")
	public Customer saveCustomers(@RequestBody Customer theCustomer  ) {
		// We have to set it null so hibernate can create new id
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
	
	@PutMapping("/customers")
	public Customer updateCustomers(@RequestBody Customer theCustomer  ) {
		// We have to set it null so hibernate can create new id
		
		customerService.saveCustomer(theCustomer);
		
		return theCustomer;
		
	}
		
	
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		
		Customer cus = customerService.getCustomer(customerId);
		
		if(cus==null) {
			throw new ObjNotFoundException("Customer Not found");
		}
		
		customerService.deleteCustomer(customerId);
		
		return "deleted "+customerId;
		
	}
	
}

