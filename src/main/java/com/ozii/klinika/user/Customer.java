package com.ozii.klinika.user;

import java.util.Arrays;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



public class Customer {

	@NotNull(message="is required")
	@Size(min=1, message="is required")	
	@Pattern(regexp = "^\\d{11}$", message = "use 11 digits")
	private String userName;
	
	@NotNull(message="is required")
	@Size(min=1, message="is required")
	private String password;

	
	private String[] authorities;
	
	public Customer() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	
}

