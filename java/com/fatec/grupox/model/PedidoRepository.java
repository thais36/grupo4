package com.fatec.grupox.model;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fatec.grupox.model.Pedido;

@SuppressWarnings("unused")
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	public List<Pedido> findByCpf(@Param("cpf") String cpf);
}