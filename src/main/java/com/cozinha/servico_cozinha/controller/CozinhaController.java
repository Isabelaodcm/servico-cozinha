package com.cozinha.servico_cozinha.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cozinha.servico_cozinha.dtos.PedidoCozinhaRequestDto;
import com.cozinha.servico_cozinha.dtos.PedidoCozinhaResponseDto;
import com.cozinha.servico_cozinha.services.CozinhaService;

@RestController
@RequestMapping("/cozinha/pedidos")
public class CozinhaController {

    private final CozinhaService service;

    public CozinhaController(CozinhaService service) {
        this.service = service;
    }

    @PostMapping
    public PedidoCozinhaResponseDto receber(@RequestBody PedidoCozinhaRequestDto dto) {
    	System.out.println("dto: "+ dto);
    	System.out.println("Pedido recebido: " + dto.id());
    	return service.receberPedido(dto);
    }

    @PutMapping("/{id}/iniciar")
    public PedidoCozinhaResponseDto iniciar(@PathVariable Long id) {
        return service.iniciarPreparo(id);
    }

    @PutMapping("/{id}/finalizar")
    public PedidoCozinhaResponseDto finalizar(@PathVariable Long id) {
        return service.finalizarPedido(id);
    }
}