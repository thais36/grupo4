package com.fatec.grupo4.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	Optional<Fornecedor> findByCnpj(String cnpj);

	List<Fornecedor> findAllByrazaoSocialIgnoreCaseContaining(String razaoSocial);
}
