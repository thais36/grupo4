
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

import com.fatec.grupo4.model.Aluno;
import com.fatec.grupo4.model.Atleta;
import com.fatec.grupo4.model.AtletaRepository;
import com.fatec.grupo4.model.Endereco;

@SuppressWarnings("unused")
@Service
public class MantemAtletaI<generated> implements MantemAtleta {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	AtletaRepository repository;

	public List<Atleta> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Atleta> consultaPorCpf(String cpf) {
		logger.info(">>>>>> servico consultaPorCpf chamado");
		return repository.findByCpf(cpf);
	}

	@Override
	public Optional<Atleta> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}

	@Override
	public Optional<Atleta> save(Atleta atleta) {
		logger.info(">>>>>> servico save chamado ");
		Optional<Atleta> umAtleta = consultaPorCpf(atleta.getCpf());
		Endereco endereco = obtemEndereco(atleta.getCep());

		if (umAtleta.isEmpty() & endereco != null) {
			logger.info(">>>>>> servico save - dados validos");
			atleta.setDataCadastro(new DateTime());
			atleta.setEndereco(endereco.getLogradouro());
			Optional<String> sexo = Optional.ofNullable(atleta.getSexo());
			if (sexo.isEmpty()) {
				logger.info(">>>>>> atleta atributo sexo => vazio");
				atleta.setSexo("M");// default
			}
			return Optional.ofNullable(repository.save(atleta));
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
	public Optional<Atleta> atualiza(Atleta atleta) {
		logger.info(">>>>>> 1.servico altera atleta chamado");
		Optional<Atleta> umAtleta = consultaPorId(atleta.getId());
		setUmAtleta(consultaPorId(atleta.getId()));
		Endereco endereco = obtemEndereco(atleta.getCep());
		if (umAtleta.isPresent() & endereco != null); {
			Atleta atletaModificado = new Atleta(atleta.getNome(), atleta.getDataNascimento(), atleta.getSexo(),
					atleta.getCpf(), atleta.getCep(), atleta.getComplemento(), atleta.getCategoria1());
			atletaModificado.setId(atleta.getId());
			atletaModificado.obtemDataAtual(new DateTime());
			atletaModificado.setEndereco(endereco.getLogradouro());
			atletaModificado.setCategoria(atleta.getCategoria());
			logger.info(">>>>>> 2. servico altera atleta cep valido para o id => " + atletaModificado.getId());
			return Optional.ofNullable(repository.save(atletaModificado));
		}
	}

	private <TODO> void setUmAtleta(Optional<Atleta> consultaPorId) {
		// TODO Auto-generated method stub;
		
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