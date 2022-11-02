package com.fatec.grupox.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fatec.grupox.model.ItemDePedido;

@SuppressWarnings("unused")
@Repository
public interface ItemDePedidoRepository extends JpaRepository<ItemDePedido, Long> {
}