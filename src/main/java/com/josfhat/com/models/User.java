package com.josfhat.com.models;

public class User {
	private String username;
	private String nickname;
	private String password;
	
	public User() {
		
	}

	public User(String username, String nickname, String password) {
		super();
		this.username = username;
		this.nickname = nickname;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
