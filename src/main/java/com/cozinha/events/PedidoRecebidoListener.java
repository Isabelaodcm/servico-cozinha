package com.cozinha.events;

import org.springframework.stereotype.Component;

import com.cozinha.dtos.PedidoCozinhaRequestDto;
import com.cozinha.services.CozinhaService;

@Component
public class PedidoRecebidoListener {

    private final CozinhaService service;

    public PedidoRecebidoListener(CozinhaService service) {
        this.service = service;
    }

    // Aqui futuramente entra RabbitMQ, Kafka etc.
    public void onPedidoRecebido(PedidoCozinhaRequestDto dto) {
        service.receberPedido(dto);
    }
}