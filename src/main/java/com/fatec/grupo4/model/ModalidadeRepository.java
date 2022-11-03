package com.fatec.grupox.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModalidadeRepository  extends JpaRepository<Modalidade, Long> {
	List<Modalidade> findAll();
	
	Optional<Modalidade> findByModalidade(String modalidade);
	List<Modalidade> findAllByModalidadeIgnoreCaseContaining(String modalidade);
	
	Optional<Modalidade> findByDiaTreino(String diaTreino);
	List<Modalidade> findAllByDiaTreinoIgnoreCaseContaining(String diaTreino);
	
	Optional<Modalidade> findByHorarioTreino(String horarioTreino);
	List<Modalidade> findAllByHorarioTreinoIgnoreCaseContaining(String horarioTreino);
	
	Optional<Modalidade> findBySexo(String sexo);
	List<Modalidade> findAllBySexoIgnoreCaseContaining(String sexo);
}
                                                                                                                                                                                                          