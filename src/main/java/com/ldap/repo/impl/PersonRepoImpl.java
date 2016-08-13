package com.ldap.repo.impl;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import com.ldap.pojo.Person;
import com.ldap.repo.PersonRepo;

@Service
public class PersonRepoImpl implements PersonRepo {
	
	@Autowired
	private LdapTemplate ldapTemplate;


	@Override
	public List<String> getAllPersonNames() {
		return ldapTemplate.search(query().where("objectclass").is("person"), new AttributesMapper<String>() {
			public String mapFromAttributes(Attributes attrs) throws NamingException {
				return (String) attrs.get("cn").get();
			}
		});
	}

	@Override
	public List<Person> getAllPersons() {
		return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
	}

	@Override
	public Person findPerson(String dn) {
		return ldapTemplate.lookup(dn, new PersonAttributesMapper());
	}
	
	@Override
	public List<String> getPersonNamesByLastName(String lastName) {

	      LdapQuery query = query()
	         .base("dc=softleader,dc=com")
	         .attributes("cn", "sn")
	         .where("objectclass").is("person")
	         .and("sn").is(lastName);

	      return ldapTemplate.search(query,
	         new AttributesMapper<String>() {
	            public String mapFromAttributes(Attributes attrs)
	               throws NamingException {
	               return (String) attrs.get("cn").get();
	            }
	         });
	   }
	
	public void create(Person person) {
		Name dn = buildDn(person);
	    ldapTemplate.bind(dn, null, buildAttributes(person));
//	    ldapTemplate.bind("cn=Rhys,dc=technology department,dc=softleader,dc=com", null, buildAttributes(person));

	}
	
	public void delete(Person person) {
		Name dn = buildDn(person);
		ldapTemplate.unbind(dn);
	}
	
	protected Name buildDn(Person person) {
		final String BASE_DN = "dc=technology department,dc=softleader,dc=com"; 
		return LdapNameBuilder.newInstance(BASE_DN)
//				.add("sn", person.getLastName())
				.add("cn", person.getName())
				.build();
	}

	private Attributes buildAttributes(Person person) {
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("person");
		attrs.put(ocattr);
//		attrs.put("dc", "technology department");
		attrs.put("cn", person.getName());
		attrs.put("sn", person.getLastName());
		return attrs;
	}

	private class PersonAttributesMapper implements AttributesMapper<Person> {
		public Person mapFromAttributes(Attributes attrs) throws NamingException {
			Person person = new Person();
			person.setName((String) attrs.get("cn").get());
			person.setLastName(attrs.get("sn") == null ? null : (String) attrs.get("sn").get());
			person.setDescription(attrs.get("description") == null? null :(String) attrs.get("description").get());
			return person;
		}
	}
}
