package com.ldap.repo.impl;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.ldap.support.LdapNameBuilder;

import com.ldap.GenericTest;
import com.ldap.pojo.Person;
import com.ldap.repo.PersonRepo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonRepoImplTest extends GenericTest{

	@Autowired
	private PersonRepo<Person> personRepo;
	
	private static String BASE_DN = "uid=137,organizationName=technology department,dc=softleader,dc=com";
	private Person person;
	
	@Before
	public void preData() {
		person = new Person();
		person.setDn(LdapNameBuilder.newInstance(BASE_DN).build());
		person.setName("Dennis");
		person.setLastName("Shen");
		person.setUid("137");
	}
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Test 
	public void test() {

	}

	@Test
	public void test1CreateMan() {
		String dn = "uid=1,dc=softleader,dc=com";
		person.setUid("1");
		person.setPassword("12345");
		person.setDn(LdapNameBuilder.newInstance(dn).build());
		personRepo.create(person);
	}
	
	@Test
	public void test2Create() {
		personRepo.create(person);
	}

	@Test
	public void test3FindByDn() {
		System.out.println(personRepo.findByDn(person.getDn(), Person.class));
	}
	
	@Test
	public void test4Update() {
		person.setPassword("12345");
		person.setDescription("say something");
		personRepo.update(person);
	}

	@Test
	public void test5FindAll() {
		personRepo.findAll(Person.class).forEach(System.out::println);
	}

	@Test
	public void test6Find() {
		String dn = "uid=1,dc=softleader,dc=com";
		LdapQuery ldapQuery = LdapQueryBuilder.query().base(dn)
				.where("objectclass").is("person");
		System.out.println(personRepo.find(ldapQuery, Person.class));
	}

	@Test
	public void test7List() {
		personRepo.list(LdapNameBuilder.newInstance(BASE_DN).build(), 3).forEach(System.out::println);
	}

	@Test
	public void testDelete() {
		personRepo.delete(person);
	}
	

}
