package com.fatec.grupo4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fatec.grupo4.model.UserAppRepository;
import com.fatec.grupo4.model.UsuarioApp;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserAppRepository repository;

	/*
	 * recebe o username e consulta no banco de dados as credenciais de autenticacao
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioApp userApp = repository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado =>" + username));
		return userApp;
	}
}