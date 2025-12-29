package com.cozinha.servico_cozinha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozinha.servico_cozinha.entities.PedidoCozinha;

public interface PedidoCozinhaRepository extends JpaRepository<PedidoCozinha, Long> {

	Optional<PedidoCozinha> findByPedidoId(Long id);
}
