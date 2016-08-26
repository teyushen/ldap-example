package com.ldap.repo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.NameClassPairMapper;
import org.springframework.ldap.support.LdapNameBuilder;

import com.ldap.repo.Repo;

public abstract class AbstractRepo implements Repo{

	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Override
	public List<Name> list(Name dn) {
		return list(dn, true);
	}
	
	@Override
	public List<Name> list(Name dn, Boolean visibleSameLeval) {
		if(visibleSameLeval) {
			String strDn = dn.toString().split(",", 2)[1];
			return ldapTemplate.list(strDn, new NameClassPairMapperImpl());
		}
		return ldapTemplate.list(dn, new NameClassPairMapperImpl());
	}

	@Override
	public List<Name> list(Name dn, Integer recursiveLimit) {
		if(recursiveLimit <= 0) {
			throw new IllegalArgumentException(String.format("can't see %s level under %s", recursiveLimit, dn)); 
		}
		List<Name> allDns = new ArrayList<>();
		List<Name> thisRoundDns = list(dn);
		
		allDns.addAll(thisRoundDns);
		for (int i = 0; i < recursiveLimit - 1; i++) {
			thisRoundDns = list(thisRoundDns);
			allDns.addAll(thisRoundDns);
		}
		return allDns;
	}
	
	protected List<Name> list(List<Name> dns) {
		List<Name> allDns = new ArrayList<>();
		dns.stream().forEach(dn -> {
			allDns.addAll(list(dn, false));
		});
		
		return allDns;
	}
	
	class NameClassPairMapperImpl implements NameClassPairMapper<Name> {
		@Override
		public Name mapFromNameClassPair(NameClassPair nameClassPair) throws NamingException {
			return LdapNameBuilder.newInstance(nameClassPair.getNameInNamespace()).build();
		}
	}
}
