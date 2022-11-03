package com.fatec.grupox.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fatec.grupox.model.Aluno;



public interface MantemAluno {
	List<Aluno> consultaTodos();

	Optional<Aluno> consultaPorCpf(String cpf);

	Optional<Aluno> consultaPorId(Long id);

	Optional<Aluno> save(Aluno aluno);

	void delete(Long id);
	
	Optional<Aluno> atualiza(@Valid Aluno aluno);
}
