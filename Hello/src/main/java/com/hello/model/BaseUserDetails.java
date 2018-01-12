package com.hello.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseUserDetails extends BaseUser implements UserDetails{

	private static final long serialVersionUID = -2339061998204343451L;

	public BaseUserDetails(BaseUser user) {
        super(user);
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<String> roleList = super.getRoleList();
		List<SimpleGrantedAuthority> sgaList = new ArrayList<SimpleGrantedAuthority>();
		
		//TODO
//		for(String role: roleList) {
//			sgaList.add(new SimpleGrantedAuthority(role));
//		}

		return sgaList;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
