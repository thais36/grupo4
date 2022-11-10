package com.fatec.grupo4.model;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class UsuarioApp implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	@Column(nullable = false, unique = true)
	private String userName;
	@Column(nullable = false)
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
// TODO Auto-generated method stub
		return null;
	}
	public void setUserName (String userName) {
		this.userName = userName;
	}
	public void setPassword(String passWord) {
		this.password = passWord;
	}
	@Override
	public String getPassword() {
// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
// TODO Auto-generated method stub
		return this.userName;
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