package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.grupo4.model.Campeonato;
import com.fatec.grupo4.model.CampeonatoRepository;

@Service
public class MantemCampeonatoI implements MantemCampeonato {
	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	CampeonatoRepository repository;

	@Override
	public Optional<Campeonato> findById(Long id) {
		Optional<Campeonato> campeonato = repository.findById(id);
		return campeonato;
	}

	@Override
	public Optional<Campeonato> findByDiaCampeonato(String diaCampeonato) {
		Optional<Campeonato> campeonato = repository.findByDiaCampeonato(diaCampeonato);
		return campeonato;
	}

	@Override
	public Optional<Campeonato> findByHorarioCampeonato(String horarioCampeonato) {
		Optional<Campeonato> campeonato = repository.findByHorarioCampeonato(horarioCampeonato);
		return campeonato;
	}

	@Override
	public Optional<Campeonato> findBySexo(String sexo) {
		Optional<Campeonato> campeonato = repository.findBySexo(sexo);
		return campeonato;
	}

	@Override
	public void save(Campeonato campeonato) {
		repository.save(campeonato);
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
	public void atualiza(Campeonato campeonato) {
		repository.save(campeonato);
	}

	@Override
	public List<Campeonato> consultaTodos() {
		return repository.findAll();
	}

	@Override
	public Optional<Campeonato> findByNome(String nome) {
		Optional<Campeonato> campeonato = repository.findByNome(nome);
		return campeonato;
	}

	@Override
	public void editar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Campeonato> consultaPorNome(String nome) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
}
