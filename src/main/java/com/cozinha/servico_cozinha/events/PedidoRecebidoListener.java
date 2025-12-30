package com.cozinha.servico_cozinha.events;

import org.springframework.stereotype.Component;

import com.cozinha.servico_cozinha.dtos.PedidoCozinhaRequestDto;
import com.cozinha.servico_cozinha.services.CozinhaService;

@Component
public class PedidoRecebidoListener {

//    private final CozinhaService service;
//
//    public PedidoRecebidoListener(CozinhaService service) {
//        this.service = service;
//    }
//
//    // Aqui futuramente entra RabbitMQ, Kafka etc.
//    public void onPedidoRecebido(PedidoCozinhaRequestDto dto) {
//        service.receberPedido(dto);
//    }
}