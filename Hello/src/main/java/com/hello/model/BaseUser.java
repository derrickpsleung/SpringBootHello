package com.hello.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BaseUser {

	@Id
    private String id;

	private String username;
	private String password;
    private List<String> roleList;

    public BaseUser() {}
    
    public BaseUser(BaseUser baseUser) {
        this.username = baseUser.getUsername();
        this.password = baseUser.getPassword();
    }

    public BaseUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public BaseUser(String username, String password, List<String> roleList) {
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

	public List<String> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public String toString() {
        return String.format(
                "BaseUser[id=%s, username='%s', password='%s']",
                id, username, password);
    }
	
}
