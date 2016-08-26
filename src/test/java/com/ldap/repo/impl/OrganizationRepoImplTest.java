package com.ldap.repo.impl;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapNameBuilder;

import com.ldap.GenericTest;
import com.ldap.pojo.Organization;
import com.ldap.repo.OrganizationRepo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrganizationRepoImplTest extends GenericTest{

	@Autowired
	private OrganizationRepo<Organization> organizationRepo;
	
	private static String BASE_DN = "organizationName=technology department,dc=softleader,dc=com";
	private Organization entry;
	
	@Before
	public void preData() {
		entry = new Organization();
		entry.setDn(LdapNameBuilder.newInstance(BASE_DN).build());
		
	}
	
	@Test
	public void test1Create() {
//		entry.setPassword("12345");
		entry.setDescription("some description");
		organizationRepo.create(entry);
	}

	@Test
	public void test2FindByDn() {
		System.out.println(organizationRepo.findByDn(entry.getDn(), Organization.class));
	}

	@Test
	public void test3FindAll() {
		organizationRepo.findAll(Organization.class).forEach(System.out::println);
	}
	
//	@Test
	public void testUpdate() {
		entry.setDescription("no description");
		organizationRepo.update(entry);
	}

	@Test
	public void test4List() {
//		BASE_DN = "organizationName=department1,organizationName=technology department,dc=softleader,dc=com";
		organizationRepo.list(LdapNameBuilder.newInstance(BASE_DN).build(), 3, true).forEach(System.out::println);
	}

//	@Test
	public void test5Delete() {
		organizationRepo.delete(entry);
	}
	



}
