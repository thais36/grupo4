package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fatec.grupo4.model.Evento;



public interface MantemEvento {
	List<Evento> consultaTodos();

	Optional<Evento> consultaPorNome(String nome);

	Optional<Evento> consultaPorId(Long id);

	Optional<Evento> save(Evento evento);

	void delete(Long id);
	
	Optional<Evento> atualiza(@Valid Evento evento);

}