package com.fatec.grupo4.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
	Optional<Evento> findByNome(String nome);

	List<Evento> findAllByNomeIgnoreCaseContaining(String nome);
}