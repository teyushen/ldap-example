package com.ldap.repo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.ldap.core.LdapTemplate;

public class App {

//	@Autowired
//	private LdapTemplate ldapTemplate;
	
	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext("spring-ldap.xml");
			PersonRepo personRepo = (PersonRepo) context.getBean("personRepo");
			LdapTemplate ldapTemplate = (LdapTemplate) context.getBean("ldapTemplate");
			personRepo.setLdapTemplate(ldapTemplate);
			System.out.println(personRepo.getAllPersonNames());
        } catch (DataAccessException e) {
            System.out.println("Error occured " + e.getCause());
        }

	}

}
