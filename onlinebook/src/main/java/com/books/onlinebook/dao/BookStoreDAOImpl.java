package com.books.onlinebook.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.books.onlinebook.entity.Book;
import com.books.onlinebook.entity.Customer;

import javax.persistence.EntityManager;

@Repository
public class BookStoreDAOImpl implements BookStoreDAO {

	// need to inject the session factory

	private EntityManager entityManager;

	@Autowired
	public BookStoreDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);
		
		// delete object with primary key
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();		
	}
	
	@SuppressWarnings("all")
	@Override
	public Object getObject(Class classObject,int theId){

		Session currentSession = entityManager.unwrap(Session.class);
		Object obj = currentSession.get(classObject, theId);
		
		return obj;
	}
	
	@SuppressWarnings("all")
	@Override
	public Object getBook( Class classObject,String isbn){

		Session currentSession = entityManager.unwrap(Session.class);
		Object obj = currentSession.get(classObject, isbn);
		
		return obj;
	}
	
	
	@Override
	public void saveObject(Object object) {

		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(object);
		
	}
	
	
	@Override
	@SuppressWarnings("all")
	public List getObjects(Class className,String propertyName, String propertyValue){
		List list = null;
		Session currentSession = entityManager.unwrap(Session.class);
		// we can use HQL querys as well 
		Criteria criteria = currentSession.createCriteria(className).setCacheable(true);
		//criteria.add(Restrictions.eq(propertyName, propertyValue));
		criteria.add(Restrictions.ilike(propertyName,"%"+propertyValue+"%"));
		list = criteria.list();
		
		return list;
		
	}
	
	
	@Override
	@SuppressWarnings("all")
	public int buyBook(String isbn){
		Session currentSession = entityManager.unwrap(Session.class);
		
		
		// todo:can be improve more. Here I am telling to staff on the first fail
		Query query = currentSession.createQuery("update Book as b set b.count = (b.count-1) where b.count>0 and b.isbn= :isbn");
		
		query.setParameter("isbn", isbn);
		int val = query.executeUpdate();
		
		System.out.println( "Inside : "+val );
		return val;
	}
	
	
	@Override
	@SuppressWarnings("all")
	public int increaseBook(Book book){
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query query = currentSession.createQuery("update Book as b set b.count = (b.count+:count) where b.count>0 and b.isbn= :isbn");
		
		query.setParameter("isbn", book.getIsbn());
		query.setParameter("count", book.getCount());
		int val = query.executeUpdate();
		
		System.out.println( "Inside : "+val );
		return val;
	}
	
	@Override
	@SuppressWarnings("all")
	public void deleteObject(Object obj){
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.delete(obj);
	}
	

}











