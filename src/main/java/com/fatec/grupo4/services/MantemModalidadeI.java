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
	public Optional<Modalidade> findById(Long id) {
		Optional<Modalidade> modalidade = repository.findById(id);
		return modalidade;
	}

	@Override
	public Optional<Modalidade> findByDiaTreino(String diaTreino) {
		Optional<Modalidade> modalidade = repository.findByDiaTreino(diaTreino);
		return modalidade;
	}

	@Override
	public Optional<Modalidade> findByHorarioTreino(String horarioTreino) {
		Optional<Modalidade> modalidade = repository.findByHorarioTreino(horarioTreino);
		return modalidade;
	}

	@Override
	public Optional<Modalidade> findBySexo(String sexo) {
		Optional<Modalidade> modalidade = repository.findBySexo(sexo);
		return modalidade;
	}

	@Override
	public void save(Modalidade modalidade) {
		repository.save(modalidade);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Override
	public void editar(String nome) {
		repository.findByNome(nome);
	}

	@Override
	public void atualiza(Modalidade modalidade) {
		repository.save(modalidade);
	}

	@Override
	public List<Modalidade> consultaTodos() {
		return repository.findAll();
	}

	@Override
	public Optional<Modalidade> findByNome(String nome) {
		Optional<Modalidade> modalidade = repository.findByNome(nome);
		return modalidade;
	}

	@Override
	public void editar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Modalidade> consultaPorNome(String nome) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
