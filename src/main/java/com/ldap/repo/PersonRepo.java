package com.ldap.repo;

import java.util.List;

import org.springframework.ldap.core.LdapTemplate;

public interface PersonRepo {

	public void setLdapTemplate(LdapTemplate ldapTemplate);
	
	public List<String> getAllPersonNames();
}
