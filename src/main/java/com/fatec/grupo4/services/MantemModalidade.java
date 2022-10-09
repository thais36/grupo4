package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import com.fatec.grupo4.model.Modalidade;


public interface MantemModalidade {
	
	List<Modalidade> consultaTodos();

	Optional<Modalidade> consultaPorId(Long id);
	
	Optional<Modalidade> findByModalidade(String modalidade);
	
	Optional<Modalidade> findByDiaTreino(String diaTreino);
	
	Optional<Modalidade> findByHorarioTreino(String horarioTreino);
	
	Optional<Modalidade> findBySexo(String sexo);

	Optional<Modalidade> save(Modalidade modalidade);

	void delete(Long id);

	Optional<Modalidade> atualiza(Modalidade Modalidade);

}