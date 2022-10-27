package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fatec.grupo4.model.Modalidade;

@Service
public interface MantemModalidade {
	
	List<Modalidade> consultaTodos();

	Optional<Modalidade> findById(Long id);
	
	Optional<Modalidade> findByNome(String nome);
	
	Optional<Modalidade> findByDiaTreino(String diaTreino);
	
	Optional<Modalidade> findByHorarioTreino(String horarioTreino);
	
	Optional<Modalidade> findBySexo(String sexo);

	void save(Modalidade modalidade);

	void delete(Long id);

	void atualiza(Modalidade Modalidade);

	void editar(Long id);

	Optional<Modalidade> consultaPorNome(String nome);

	void editar(String nome);




}