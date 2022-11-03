package com.fatec.grupox.services;

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

import com.fatec.grupox.model.Endereco;
import com.fatec.grupox.model.Fornecedor;
import com.fatec.grupox.model.FornecedorRepository;

@Service
public class MantemFornecedorI implements MantemFornecedor {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	FornecedorRepository repository;

	private Optional<Fornecedor> umFornecedor;

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
		setUmFornecedor(consultaPorId(fornecedor.getId()));
		Endereco endereco = obtemEndereco(fornecedor.getCep());
		//if (umFornecedor.isPresent() & endereco != null) {
		Fornecedor fornecedorModificado = new Fornecedor(fornecedor.getrazaoSocial(),
				fornecedor.getCnpj(), fornecedor.getCep(), fornecedor.getEndereco());
			fornecedorModificado.setId(fornecedor.getId());
			fornecedorModificado.obtemDataAtual(new DateTime());
			fornecedorModificado.setEndereco(endereco.getLogradouro());
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
			logger.info(">>>>>> consulta CEP inválido erro HttpClientErrorException =>" + e.getMessage());
			return null;
		}
	}

	public Optional<Fornecedor> getUmFornecedor() {
		return umFornecedor;
	}

	public void setUmFornecedor(Optional<Fornecedor> umFornecedor) {
		this.umFornecedor = umFornecedor;
	}
	
}





