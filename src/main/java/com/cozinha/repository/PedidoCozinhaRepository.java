package com.cozinha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cozinha.entities.PedidoCozinha;

public interface PedidoCozinhaRepository extends JpaRepository<PedidoCozinha, Long> {
}
