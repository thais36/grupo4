package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.grupo4.model.Modalidade;
import com.fatec.grupo4.model.ModalidadeRepository;

@Service
public class MantemModalidadeI implements MantemModalidade {
	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	ModalidadeRepository repository;

	@Override
	public List<Modalidade> consultaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Modalidade> consultaPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Modalidade> findByModalidade(String modalidade) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Modalidade> findByDiaTreino(String diaTreino) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Modalidade> findByHorarioTreino(String horarioTreino) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Modalidade> findBySexo(String sexo) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Modalidade> save(Modalidade modalidade) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Modalidade> atualiza(Modalidade Modalidade) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
