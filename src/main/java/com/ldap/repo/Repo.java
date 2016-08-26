package com.ldap.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

import org.springframework.ldap.core.NameClassPairMapper;
import org.springframework.ldap.support.LdapNameBuilder;

public interface Repo {

	public List<Name> list(Name dn);

	public List<Name> list(Name dn, Boolean visibleSameLeval);

	public List<Name> list(Name dn, Integer recursiveLimit);

	default List<Name> list(List<Name> dns) {
		List<Name> allDns = new ArrayList<>();
		dns.stream().forEach(dn -> {
			allDns.addAll(list(dn, false));
		});
		
		return allDns.stream().distinct().collect(Collectors.toList());
	}
	
	class NameClassPairMapperImpl implements NameClassPairMapper<Name> {
		@Override
		public Name mapFromNameClassPair(NameClassPair nameClassPair) throws NamingException {
			return LdapNameBuilder.newInstance(nameClassPair.getNameInNamespace()).build();
		}
	}
}
