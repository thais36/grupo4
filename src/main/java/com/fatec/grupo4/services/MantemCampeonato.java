package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fatec.grupo4.model.Campeonato;



public interface MantemCampeonato {
	List<Campeonato> consultaTodos();

	Optional<Campeonato> consultaPorNome(String nome);

	Optional<Campeonato> consultaPorId(Long id);

	Optional<Campeonato> save(Campeonato campeonato);

	void delete(Long id);
	
	Optional<Campeonato> atualiza(@Valid Campeonato campeonato);

}