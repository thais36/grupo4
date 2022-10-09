package com.fatec.grupo4.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Optional<Produto> findById(String id);

	List<Produto> findAllByNomeIgnoreCaseContaining(String nome);
}