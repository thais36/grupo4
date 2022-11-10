package com.fatec.grupo4.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fatec.grupo4.model.ItemDePedido;

@SuppressWarnings("unused")
@Repository
public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long> {
}