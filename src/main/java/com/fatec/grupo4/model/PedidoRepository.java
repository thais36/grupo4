package com.fatec.grupo4.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import com.fatec.grupo4.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long> {
	
	public List<Pedido> findByCpf(@Param("cpf") String cpf);
	
	Optional<Pedido> findById(Long id);
	
}