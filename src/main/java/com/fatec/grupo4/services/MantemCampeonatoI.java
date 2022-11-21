package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.fatec.grupo4.model.Campeonato;
import com.fatec.grupo4.model.CampeonatoRepository;
import com.fatec.grupo4.model.Endereco;

@Service
public class MantemCampeonatoI implements MantemCampeonato {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	CampeonatoRepository repository;

	public List<Campeonato> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Campeonato> consultaPorNome (String nome) {
		logger.info(">>>>>> servico consultaPorNomeCampeonato chamado");
		return repository.findByNome(nome);
	}

	@Override
	public Optional<Campeonato> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}

	@Override
	public Optional<Campeonato> save(Campeonato campeonato) {
		logger.info(">>>>>> servico save chamado ");
		Optional<Campeonato> umCampeonato = consultaPorNome(campeonato.getNome());
		Endereco endereco = obtemEndereco(campeonato.getCep());

		if (umCampeonato.isEmpty() & endereco != null) {
			logger.info(">>>>>> servico save - dados validos");
			campeonato.setDataCadastro(new DateTime());
			campeonato.setEndereco(endereco.getLogradouro());
			return Optional.ofNullable(repository.save(campeonato));
		} else {
			return Optional.empty();
		}

	}

	@Override
	public void delete(Long id) {
		logger.info(">>>>>> servico delete por id chamado");
		repository.deleteById(id);
	}

	@Override
	public Optional<Campeonato> atualiza(Campeonato campeonato) {
		logger.info(">>>>>> 1.servico altera campeonato chamado");
		Optional<Campeonato> umCampeonato = consultaPorId(campeonato.getId());
		Endereco endereco = obtemEndereco(campeonato.getCep());
		if (umCampeonato.isPresent() & endereco != null) {
			Campeonato campeonatoModificado = new Campeonato(campeonato.getNome(), campeonato.getDataCampeonato(), campeonato.getHoraCampeonato(),
			campeonato.getCep(), campeonato.getComplemento(), campeonato.getCategoria1());
			campeonatoModificado.setId(campeonato.getId());
			campeonatoModificado.obtemDataAtual(new DateTime());
			campeonatoModificado.setEndereco(endereco.getLogradouro());
			campeonatoModificado.setCategoria(campeonato.getCategoria());
			logger.info(">>>>>> 2. servico altera campeonato cep valido para o id => " + campeonatoModificado.getId());
			return Optional.ofNullable(repository.save(campeonatoModificado));
					}
		return umCampeonato;
	}

	public Endereco obtemEndereco(String cep) {
		RestTemplate template = new RestTemplate();
		String url = "https://viacep.com.br/ws/{cep}/json/";
		logger.info(">>>>>> servico consultaCep - " + cep);
		ResponseEntity<Endereco> resposta = null;
		try {
			resposta = template.getForEntity(url, Endereco.class, cep);
			return resposta.getBody();
		} catch (ResourceAccessException e) {
			logger.info(">>>>>> consulta CEP erro nao esperado ");
			return null;
		} catch (HttpClientErrorException e) {
			logger.info(">>>>>> consulta CEP inválido erro HttpClientErrorException =>" + e.getMessage());
			return null;
		}
	}
	
}