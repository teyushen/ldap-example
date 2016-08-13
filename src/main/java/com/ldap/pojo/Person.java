package com.ldap.pojo;

public class Person {

	private String name;
	
	private String lastName;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", lastName=" + lastName + ", description=" + description + "]";
	}
	
	
}
