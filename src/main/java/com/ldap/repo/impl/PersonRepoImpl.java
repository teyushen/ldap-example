package com.ldap.repo.impl;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ldap.pojo.Person;
import com.ldap.repo.PersonRepo;

@Repository
public class PersonRepoImpl extends AbstractRepo implements PersonRepo<Person> {
	
	@Autowired
	private LdapTemplate ldapTemplate;

	@Override
	public Person findByDn(Name dn, Class<Person> clazz) {
		return ldapTemplate.findByDn(dn, clazz);
	}

	@Override
	public Person findOne(LdapQuery query, Class<Person> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> find(LdapQuery query, Class<Person> clazz) {
		return ldapTemplate.find(query, clazz);
	}

	@Override
	public List<Person> findAll(Class<Person> clazz) {
		return ldapTemplate.findAll(clazz);
	}

	@Override
	public Person create(Person entry) {
		ldapTemplate.create(entry);
		return entry;
	}

	@Override
	public void update(Person entry) {
		ldapTemplate.update(entry);
		
	}

	@Override
	public void delete(Person entry) {
		ldapTemplate.delete(entry);
		
	}

	/////////////////////////////////////////////////////////////////////////////
	
	
//	@Override
//	public List<String> getAllPersonNames() {
//		return ldapTemplate.search(query().where("objectclass").is("person"), new AttributesMapper<String>() {
//			public String mapFromAttributes(Attributes attrs) throws NamingException {
//				return (String) attrs.get("cn").get();
//			}
//		});
//	}
//
//	@Override
//	public List<Person> getAllPersons() {
//		return ldapTemplate.search(query().where("objectclass").is("person"), new PersonAttributesMapper());
//	}
//
//	@Override
//	public Person findPerson(String dn) {
//		return ldapTemplate.lookup(dn, new PersonAttributesMapper());
//	}
//	
//	@Override
//	public List<String> getPersonNamesByLastName(String lastName) {
//
//	      LdapQuery query = query()
//	         .base("dc=softleader,dc=com")
//	         .attributes("cn", "sn")
//	         .where("objectclass").is("person")
//	         .and("sn").is(lastName);
//
//	      return ldapTemplate.search(query,
//	         new AttributesMapper<String>() {
//	            public String mapFromAttributes(Attributes attrs)
//	               throws NamingException {
//	               return (String) attrs.get("cn").get();
//	            }
//	         });
//	   }
//	
//	public void create(Person person) {
//		Name dn = buildDn(person);
//	    ldapTemplate.bind(dn, null, buildAttributes(person));
////	    ldapTemplate.bind("cn=Rhys,dc=technology department,dc=softleader,dc=com", null, buildAttributes(person));
//
//	}
//	
//	public void delete(Person person) {
//		Name dn = buildDn(person);
//		ldapTemplate.unbind(dn);
//	}
//	
//	protected Name buildDn(Person person) {
//		final String BASE_DN = "ou=technology department,dc=softleader,dc=com"; 
//		return LdapNameBuilder.newInstance(BASE_DN)
////				.add("sn", person.getLastName())
//				.add("cn", person.getName())
//				.build();
//	}
//
//	@Override
//	public void authenticate(Person person) {
//		AuthenticatedLdapEntryContextMapper<DirContextOperations> mapper = new AuthenticatedLdapEntryContextMapper<DirContextOperations>() {
//			  public DirContextOperations mapWithContext(DirContext ctx, LdapEntryIdentification ldapEntryIdentification) {
//			    try {
//			      return (DirContextOperations) ctx.lookup(ldapEntryIdentification.getRelativeName());
//			    }
//			    catch (NamingException e) {
//			      throw new RuntimeException("Failed to lookup " + ldapEntryIdentification.getRelativeName(), e);
//			    }
//			  }
//			};
//		DirContextOperations dco = ldapTemplate.authenticate(query().where("uid").is(person.getUid()),
//				person.getPassword(), mapper);
//		System.out.println(dco.getNameInNamespace());
////		ldapTemplate.authenticate(query().where("uid").is(person.getUid()), person.getPassword());
//	
//	}
//	
//	private Attributes buildAttributes(Person person) {
//		Attributes attrs = new BasicAttributes();
//		BasicAttribute ocattr = new BasicAttribute("objectclass");
//		ocattr.add("top");
//		ocattr.add("person");
//		attrs.put(ocattr);
//		attrs.put("description", person.getDescription());
//		attrs.put("cn", person.getName());
//		attrs.put("sn", person.getLastName());
//		return attrs;
//	}
//
//	private class PersonAttributesMapper implements AttributesMapper<Person> {
//		public Person mapFromAttributes(Attributes attrs) throws NamingException {
//			Person person = new Person();
//			person.setName((String) attrs.get("cn").get());
//			person.setLastName(attrs.get("sn") == null ? null : (String) attrs.get("sn").get());
//			person.setDescription(attrs.get("description") == null? null :(String) attrs.get("description").get());
//			return person;
//		}
//	}

}
