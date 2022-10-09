package com.fatec.grupo4.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadeRepository  extends JpaRepository<Modalidade, Long> {
	List<Modalidade> consultaTodos();
	
	Optional<Modalidade> findByModalidade(String modalidade);
	
	Optional<Modalidade> findByDiaTreino(String diaTreino);
	
	Optional<Modalidade> findByHorarioTreino(String horarioTreino);
	
	Optional<Modalidade> findBySexo(String sexo);
}
