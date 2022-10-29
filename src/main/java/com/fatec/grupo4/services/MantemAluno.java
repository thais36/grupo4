package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import com.fatec.grupo4.model.Aluno;



public interface MantemAluno {
	List<Aluno> consultaTodos();

	Optional<Aluno> consultaPorCpf(String cpf);

	Optional<Aluno> consultaPorId(Long id);

	Optional<Aluno> save(Aluno aluno);

	void delete(Long id);

	Optional<Aluno> atualiza(Aluno aluno);
}
