package com.fatec.grupo4.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAppRepository extends JpaRepository<UsuarioApp, Long> {
	Optional<UsuarioApp> findByUserName(String usarName);
}