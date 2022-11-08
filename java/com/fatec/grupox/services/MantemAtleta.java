package com.fatec.grupox.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fatec.grupox.model.Atleta;

public interface MantemAtleta {
	
	List<Atleta> consultaTodos();

	Optional<Atleta> consultaPorCpf(String cpf);

	Optional<Atleta> consultaPorId(Long id);

	Optional<Atleta> save(Atleta atleta);

	void delete(Long id);

	Optional<Atleta> atualiza(@Valid Atleta atleta);	
}
