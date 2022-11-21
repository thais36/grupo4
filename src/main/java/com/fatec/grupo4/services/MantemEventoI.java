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

import com.fatec.grupo4.model.Evento;
import com.fatec.grupo4.model.EventoRepository;
import com.fatec.grupo4.model.Endereco;

@Service
public class MantemEventoI implements MantemEvento {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	EventoRepository repository;

	public List<Evento> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Evento> consultaPorNome (String nome) {
		logger.info(">>>>>> servico consultaPorNomeEvento chamado");
		return repository.findByNome(nome);
	}

	@Override
	public Optional<Evento> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}

	@Override
	public Optional<Evento> save(Evento evento) {
		logger.info(">>>>>> servico save chamado ");
		Optional<Evento> umEvento = consultaPorNome(evento.getNome());
		Endereco endereco = obtemEndereco(evento.getCep());

		if (umEvento.isEmpty() & endereco != null) {
			logger.info(">>>>>> servico save - dados validos");
			evento.setDataCadastro(new DateTime());
			evento.setEndereco(endereco.getLogradouro());
			return Optional.ofNullable(repository.save(evento));
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
	public Optional<Evento> atualiza(Evento evento) {
		logger.info(">>>>>> 1.servico altera evento chamado");
		Optional<Evento> umEvento = consultaPorId(evento.getId());
		Endereco endereco = obtemEndereco(evento.getCep());
		if (umEvento.isPresent() & endereco != null) {
			Evento eventoModificado = new Evento(evento.getNome(), evento.getDataEvento(), evento.getHoraEvento(),
			evento.getCep(), evento.getComplemento(), evento.getCategoria1());
			eventoModificado.setId(evento.getId());
			eventoModificado.obtemDataAtual(new DateTime());
			eventoModificado.setEndereco(endereco.getLogradouro());
			eventoModificado.setCategoria(evento.getCategoria());
			logger.info(">>>>>> 2. servico altera evento cep valido para o id => " + eventoModificado.getId());
			return Optional.ofNullable(repository.save(eventoModificado));
					}
		return umEvento;
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