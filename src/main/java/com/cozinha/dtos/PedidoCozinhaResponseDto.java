package com.cozinha.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.cozinha.entities.StatusCozinha;

public record PedidoCozinhaResponseDto(         
		Long id,
        StatusCozinha status,
        LocalDateTime dataHora,
        List<ItemPedidoDto> itens) {

}
