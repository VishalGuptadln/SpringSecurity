package com.JwtToken.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="USER_TBL")
public class User {
	
	@Id
	private String username;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	private String mobileNO;
	private String password;
	private String email;
	private String role;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMobileNO() {
		return mobileNO;
	}
	public void setMobileNO(String mobileNO) {
		this.mobileNO = mobileNO;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole_name(String role) {
		this.role = role;
	}
	
	public User(String username, Date dob, String mobileNO, String password, String email, String role) {
		super();
		this.username = username;
		this.dob = dob;
		this.mobileNO = mobileNO;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", dob=" + dob + ", mobileNO=" + mobileNO + ", password=" + password
				+ ", email=" + email + ", role=" + role + "]";
	}
	public boolean checkNull(){
		
		if(this.username==null || this.dob==null ||  this.mobileNO==null || this.password==null || this.email==null || this.role==null)
		{
			return false;
		}
		if(this.username.equals("") || this.dob.equals("") ||  this.mobileNO.equals("") || this.password.equals("") || this.email.equals("") || this.role.equals(""))
		{
			return false;
		}
//	    for (java.lang.reflect.Field f : getClass().getDeclaredFields())
//	    {
//	        if (f.get(this) == null)
//	            return false;
//	        if(f.get(this).equals(""))
//	        	return false;
//	    }
	    return true;            
	}
	

}
