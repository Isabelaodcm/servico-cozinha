package com.cozinha.servico_cozinha.dtos;

import java.util.List;

public record PedidoCozinhaRequestDto(
        Long id, //id do pedido
        Long clienteId,
        List<ItemPedidoDto> itens
) {}
