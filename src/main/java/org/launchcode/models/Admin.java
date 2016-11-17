package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends AbstractUser{
	
	//No arg constructor for Hibernate
	public Admin(){}
	
	public Admin(String firstName, String lastName, String username, String password){
		super();
	}
	
	public String adminFullName(String firstName, String lastName){
		String adminFullName = firstName + " " + lastName;
		return adminFullName;
	}
	
	//setup methods to add evaluators to evaluator db
	
	
	

}
