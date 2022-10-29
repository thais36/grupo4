package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatec.grupo4.model.Campeonato;

@Service
public interface MantemCampeonato {
	
	List<Campeonato> consultaTodos();

	Optional<Campeonato> findById(Long id);
	
	Optional<Campeonato> findByNome(String nome);
	
	Optional<Campeonato> findByDiaCampeonato(String diaCampeonato);
	
	Optional<Campeonato> findByHorarioCampeonato(String horarioCampeonato);
	
	Optional<Campeonato> findBySexo(String sexo);

	void save(Campeonato campeonato);

	void delete(Long id);

	void atualiza(Campeonato campeonato);

	void editar(Long id);

	Optional<Campeonato> consultaPorNome(String nome);

	void editar(String nome);




}