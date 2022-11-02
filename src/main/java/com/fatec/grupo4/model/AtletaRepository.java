package com.fatec.grupox.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtletaRepository extends JpaRepository<Atleta, Long> {
	Optional<Atleta> findByCpf(String cpf);

	List<Atleta> findAllByNomeIgnoreCaseContaining(String nome);
}

