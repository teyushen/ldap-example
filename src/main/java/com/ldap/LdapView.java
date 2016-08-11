package com.ldap;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

public class LdapView {
	
	public static void main(String[] args) {
		String url = "ldap://localhost:10389";
		String base = "dc=example,dc=com";
		String userDn = "uid=admin,ou=system";
		String password = "secret";
		try{
			LdapContextSource  ctxSrc = new LdapContextSource();
			ctxSrc.setUrl(url);
			ctxSrc.setBase(base);
			ctxSrc.setUserDn(userDn);
			ctxSrc.setPassword(password);
			ctxSrc.afterPropertiesSet();
			LdapTemplate ldapTemplate = new LdapTemplate(ctxSrc);
			List<String> l = ldapTemplate.search(
			         query().where("objectclass").is("person"),
			         new AttributesMapper<String>() {
			            public String mapFromAttributes(Attributes attrs)
			               throws NamingException {
			               return (String) attrs.get("cn").get();
			            }
			         });
			System.out.println(l);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
