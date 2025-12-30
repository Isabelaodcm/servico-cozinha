package com.cozinha.servico_cozinha.events;

public record PedidoStatusEvent(
        Long pedidoId,
        Long clienteId,
        String status
) {}

