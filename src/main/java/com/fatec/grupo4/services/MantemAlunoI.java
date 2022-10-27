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
import com.fatec.grupo4.model.AlunoRepository;
import com.fatec.grupo4.model.Endereco;

@Service
public class MantemAlunoI implements MantemAluno {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	AlunoRepository repository;

	public List<Aluno> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Aluno> consultaPorCpf(String cpf) {
		logger.info(">>>>>> servico consultaPorCpf chamado");
		return repository.findByCpf(cpf);
	}

	@Override
	public Optional<Aluno> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}

	@Override
	public Optional<Aluno> save(Aluno aluno) {
		logger.info(">>>>>> servico save chamado ");
		Optional<Aluno> umAluno = consultaPorCpf(aluno.getCpf());
		Endereco endereco = obtemEndereco(aluno.getCep());

		if (umAluno.isEmpty() & endereco != null) {
			logger.info(">>>>>> servico save - dados validos");
			aluno.setDataCadastro(new DateTime());
			aluno.setEndereco(endereco.getLogradouro());
			Optional<String> sexo = Optional.ofNullable(aluno.getSexo());
			if (sexo.isEmpty()) {
				logger.info(">>>>>> aluno atributo sexo => vazio");
				aluno.setSexo("M");// default
			}
			return Optional.ofNullable(repository.save(aluno));
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
	public Optional<Aluno> atualiza(Aluno aluno) {
		logger.info(">>>>>> 1.servico altera aluno chamado");
		Optional<Aluno> umAluno = consultaPorId(aluno.getId());
		Endereco endereco = obtemEndereco(aluno.getCep());
		//if (umAluno.isPresent() & endereco != null) {
			Aluno alunoModificado = new Aluno(aluno.getNome(), aluno.getDataNascimento(), aluno.getSexo(),
					aluno.getCpf(), aluno.getCep(), aluno.getComplemento(), aluno.getCategoria1());
			alunoModificado.setId(aluno.getId());
			alunoModificado.obtemDataAtual(new DateTime());
			alunoModificado.setEndereco(endereco.getLogradouro());
			alunoModificado.setCategoria(aluno.getCategoria());
			logger.info(">>>>>> 2. servico altera aluno cep valido para o id => " + alunoModificado.getId());
			return Optional.ofNullable(repository.save(alunoModificado));
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




