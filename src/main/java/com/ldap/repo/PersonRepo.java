package com.ldap.repo;

import java.util.List;

import com.ldap.pojo.Person;

public interface PersonRepo {

	public List<String> getAllPersonNames();
	
	public List<Person> getAllPersons();

	public Person findPerson(String dn);
	
	public List<String> getPersonNamesByLastName(String lastName);
	
	public void create(Person person);
	
	public void delete(Person person);
}
