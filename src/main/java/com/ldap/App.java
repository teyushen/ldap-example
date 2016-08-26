package com.ldap;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

import com.ldap.pojo.Person;
import com.ldap.repo.PersonRepo;

public class App {
	
	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("spring-ldap.xml");
			PersonRepo personRepo = (PersonRepo) context.getBean("personRepo");
			
			Person person = new Person();
			person.setName("Rhys");
			person.setLastName("Chang");
			person.setDescription("no description");
			personRepo.create(person);
//			System.out.println(personRepo.getAllPersonNames());
			personRepo.delete(person);
			
//			System.out.println(personRepo.getAllPersons());
//			System.out.println(personRepo.findPerson("cn=Dennis+description=something,dc=technology department,dc=softleader,dc=com"));
//			System.out.println(personRepo.getPersonNamesByLastName("Shen"));
        } catch (DataAccessException e) {
            System.out.println("Error occured " + e.getCause());
        }

	}

}
