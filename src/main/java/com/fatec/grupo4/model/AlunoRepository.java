package com.fatec.grupo4.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	Optional<Aluno> findByCpf(String cpf);

	List<Aluno> findAllByNomeIgnoreCaseContaining(String nome);
}
