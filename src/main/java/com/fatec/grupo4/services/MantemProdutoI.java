package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.grupo4.model.Produto;
import com.fatec.grupo4.model.ProdutoRepository;

@Service
public class MantemProdutoI implements MantemProduto {
	
	Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	ProdutoRepository repository;
	
	@Override
	public List<Produto> consultaTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Produto> consultaPorId(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public Optional<Produto> consultaPorNome(String nome) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public Optional<Produto> consultaPorTamanho(String tamanho) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public Optional<Produto> consultaPorCor(String cor) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public Optional<Produto> save(Produto produto) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Optional<Produto> atualiza(Long id, Produto produto) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
}