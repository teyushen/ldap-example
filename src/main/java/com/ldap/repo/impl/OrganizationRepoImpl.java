package com.ldap.repo.impl;

import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import com.ldap.pojo.Organization;
import com.ldap.repo.OrganizationRepo;

@Repository
public class OrganizationRepoImpl extends AbstractRepo implements OrganizationRepo<Organization>{

	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public Organization findByDn(Name dn, Class<Organization> clazz) {
		return ldapTemplate.findByDn(dn, clazz);
	}

	@Override
	public Organization findOne(LdapQuery query, Class<Organization> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Organization> find(LdapQuery query, Class<Organization> clazz) {
		return ldapTemplate.find(query, clazz);
	}

	@Override
	public List<Organization> findAll(Class<Organization> clazz) {
		return ldapTemplate.findAll(Organization.class);
	}

	@Override
	public Organization create(Organization entry) {
		ldapTemplate.create(entry);
		return entry;
	}

	@Override
	public void update(Organization entry) {
		ldapTemplate.update(entry);
		
	}

	@Override
	public void delete(Organization entry) {
		ldapTemplate.delete(entry);
		
	}
	


}
