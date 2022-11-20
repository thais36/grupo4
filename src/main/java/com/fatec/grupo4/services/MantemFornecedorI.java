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

import com.fatec.grupo4.model.Fornecedor;
import com.fatec.grupo4.model.FornecedorRepository;
import com.fatec.grupo4.model.Endereco;

@Service
public class MantemFornecedorI implements MantemFornecedor {

	Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	FornecedorRepository repository;

	public List<Fornecedor> consultaTodos() {
		logger.info(">>>>>> servico consultaTodos chamado");
		return repository.findAll();
	}

	@Override
	public Optional<Fornecedor> consultaPorCnpj(String cnpj) {
		logger.info(">>>>>> servico consultaPorCnpj chamado");
		return repository.findByCnpj(cnpj);
	}

	@Override
	public Optional<Fornecedor> consultaPorId(Long id) {
		logger.info(">>>>>> servico consultaPorId chamado");
		return repository.findById(id);
	}

	@Override
	public Optional<Fornecedor> save(Fornecedor fornecedor) {
		logger.info(">>>>>> servico save chamado ");
		Optional<Fornecedor> umFornecedor = consultaPorCnpj(fornecedor.getCnpj());
		Endereco endereco = obtemEndereco(fornecedor.getCep());

		if (umFornecedor.isEmpty() & endereco != null) {
			logger.info(">>>>>> servico save - dados validos");
			fornecedor.setDataCadastro(new DateTime());
			fornecedor.setEndereco(endereco.getLogradouro());
			fornecedor.setCnpj(fornecedor.getCnpj().replace(".", "").replace("/", "").replace("-", ""));
			repository.save(fornecedor);
			return repository.findByCnpj(fornecedor.getCnpj());
		}
		return Optional.empty();
	}

	@Override
	public void delete(Long id) {
		logger.info(">>>>>> servico delete por id chamado");
		repository.deleteById(id);
	}

	@Override
	public Optional<Fornecedor> atualiza(Fornecedor fornecedor) {
		logger.info(">>>>>> 1.servico altera fornecedor chamado");
		@SuppressWarnings("unused")
		Optional<Fornecedor> umFornecedor = consultaPorId(fornecedor.getId());
		Endereco endereco = obtemEndereco(fornecedor.getCep());
		// if (umFornecedor.isPresent() & endereco != null) {
		Fornecedor fornecedorModificado = new Fornecedor(fornecedor.getRazaoSocial(), fornecedor.getCnpj(),
				fornecedor.getCep(), fornecedor.getComplemento(), fornecedor.getCategoria());
		fornecedorModificado.setId(fornecedor.getId());
		fornecedorModificado.obtemDataAtual(new DateTime());
		fornecedorModificado.setEndereco(endereco.getLogradouro());
		fornecedorModificado.setCategoria(fornecedor.getCategoria());
		logger.info(">>>>>> 2. servico altera fornecedor cep valido para o id => " + fornecedorModificado.getId());
		return Optional.ofNullable(repository.save(fornecedorModificado));
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
			logger.info(">>>>>> consulta CEP inv�lido erro HttpClientErrorException =>" + e.getMessage());
			return null;
		}
	}

}
