package com.cozinha.dtos;

import java.util.List;

public record PedidoCozinhaRequestDto(
        Long id,
        Long clienteId,
        List<ItemPedidoDto> itens
) {}
