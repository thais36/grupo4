package com.fatec.grupox.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.fatec.grupox.model.Modalidade;
import com.fatec.grupox.model.Produto;

@SuppressWarnings("unused")
public interface MantemProduto {
	
	List<Produto> consultaTodos();

	Optional<Produto> consultaPorId(Long id);

	Optional<Produto> consultaPorNome(String nome);

	Optional<Produto> consultaPorTamanho(String tamanho);
	
	//Optional<Produto> consultaPorTipo(String tipo);
	
	Optional<Produto> consultaPorCor(String cor);


	void delete(Long id);
	
	void editar(Long id);

	Optional<Produto> atualiza(Produto produto);

	Optional<Produto> atualiza(Long id, Produto produto);

	void save(@Valid Produto produto);

}



