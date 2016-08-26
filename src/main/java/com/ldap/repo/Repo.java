package com.ldap.repo;

import java.util.List;

import javax.naming.Name;

public interface Repo {

	public List<Name> list(Name dn);

	public List<Name> list(Name dn, Boolean visibleSameLeval);

	public List<Name> list(Name dn, Integer recursiveLimit);
	
	public List<Name> list(Name dn, Integer recursiveLimit, Boolean visibleSameLeval);

	
}
