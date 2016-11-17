package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="evaluator")
public class Evaluator extends AbstractUser{
	
	//no arg constructor for Hibernate
	public Evaluator(){
		
	}
	
	public Evaluator (String firstName, String lastName, String username, String password){
		super();
	}
	
	//set up methods to review official's previous evaluations
	public String evaluatorFullName(String firstName, String lastName){
		String evaluatorFullName = firstName + " " + lastName;
		return evaluatorFullName;
	}
	


}
