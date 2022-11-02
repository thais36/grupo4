package com.fatec.grupox.services;

import java.util.List;
import java.util.Optional;

import com.fatec.grupox.model.Produto;

public interface MantemProduto {
	
	List<Produto> consultaTodos();

	Optional<Produto> consultaPorId(Long id);

	Optional<Produto> consultaPorNome(String nome);

	Optional<Produto> consultaPorTamanho(String tamanho);
	
	//Optional<Produto> consultaPorTipo(String tipo);
	
	Optional<Produto> consultaPorCor(String cor);

	Optional<Produto> save(Produto produto);

	void delete(Long id);

	Optional<Produto> atualiza(Produto produto);

	Optional<Produto> atualiza(Long id, Produto produto);

}



