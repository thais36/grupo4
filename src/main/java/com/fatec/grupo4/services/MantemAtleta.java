package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;
import com.fatec.grupo4.model.Atleta;

public interface MantemAtleta {
	
	List<Atleta> consultaTodos();

	Optional<Atleta> consultaPorCpf(String cpf);

	Optional<Atleta> consultaPorId(Long id);

	Optional<Atleta> save(Atleta atleta);

	void delete(Long id);

	Optional<Atleta> atualiza(Atleta atleta);
	

}
