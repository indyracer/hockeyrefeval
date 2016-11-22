package org.launchcode.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@MappedSuperclass
public class AbstractUser {
	
	private int uid;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	protected String pwHash;
	protected static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
	
	
	
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "uid", unique = true)
	public int getUid(){
		return this.uid;
	}
	
	protected void setUid(int uid){
		this.uid = uid;
	}
	
	@NotNull
	@Column(name = "first_name")
	public String getFirstName(){
		return this.firstName;
	}
	
	protected void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	@NotNull
	@Column(name = "last_name")
	public String getLastName(){
		return this.lastName;
	}
	
	protected void setLastName (String lastName){
		this.lastName = lastName;
	}
	
	@NotNull
	@Column(name = "username", unique = true)
	public String getUsername(){
		return this.username;
	}
	
	protected void setUsername (String username){
		this.username = username;
	}
	
	@NotNull
	@Column(name = "pwhash")
	public String getPwhash(){
		pwHash = hashPassword(password);
		return this.pwHash;
	}
	
	private static String hashPassword(String password){
		return encoder.encode(password);
	}
	
	//checks that submitted password is correct for login
	public boolean isMatchingPassword(String password){
		return encoder.matches(password, pwHash);
	}
	
	

}
