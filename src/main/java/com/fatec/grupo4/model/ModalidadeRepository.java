package com.fatec.grupo4.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModalidadeRepository  extends JpaRepository<Modalidade, Long> {

	Optional<Modalidade> findById(Long id);
	
	Optional<Modalidade> findByNome(String nome);

	Optional<Modalidade> findByDiaTreino(String diaTreino);
	
	Optional<Modalidade> findByHorarioTreino(String horarioTreino);
	
	Optional<Modalidade> findBySexo(String sexo);
}
                                                                                                                                                                                                          