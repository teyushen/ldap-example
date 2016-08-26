package com.ldap.repo;

import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.query.LdapQuery;

import com.ldap.pojo.Organization;

public interface OrganizationRepo<T extends Organization> extends Repo{

	public T findByDn(Name dn, Class<T> clazz);

	public T findOne(LdapQuery query, Class<T> clazz);

	public List<T> find(LdapQuery query, Class<T> clazz);

	public List<T> findAll(Class<T> clazz);

//	public List<T> findAll(Name base, SearchControls searchControls, Class<T> clazz);

//	public List<T> findAll(Name base, Filter filter, SearchControls searchControls, Class<T> clazz);

	public T create(T entry);

	public void update(T entry);

	public void delete(T entry);
	
	/**
	 * 
	 * @param dn
	 * @param recursiveLimit
	 * @param visibleSameLeval
	 * @return
	 */
	public List<Name> list(Name dn, Integer recursiveLimit, Boolean visibleSameLeval);
	
}
