package com.fatec.grupo4.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampeonatoRepository  extends JpaRepository<Campeonato, Long> {

	Optional<Campeonato> findById(Long id);
	
	Optional<Campeonato> findByNome(String nome);

	Optional<Campeonato> findByDiaCampeonato(String diaCampeonato);
	
	Optional<Campeonato> findByHorarioCampeonato(String horarioCampeonato);
	
	Optional<Campeonato> findBySexo(String sexo);
}