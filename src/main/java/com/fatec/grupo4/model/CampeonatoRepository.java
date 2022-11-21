package com.fatec.grupo4.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
	Optional<Campeonato> findByNome(String nome);

	List<Campeonato> findAllByNomeIgnoreCaseContaining(String nome);
}