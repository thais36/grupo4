package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import com.fatec.grupo4.model.Fornecedor ;

public interface MantemFornecedor  {
	
	List<Fornecedor> consultaTodos();

	Optional<Fornecedor> consultaPorCnpj(String cnpj);

	Optional<Fornecedor> consultaPorId(Long id);

	Optional<Fornecedor> save(Fornecedor fornecedor);

	void delete(Long id);

	Optional<Fornecedor> atualiza(Fornecedor fornecedor);
	
}