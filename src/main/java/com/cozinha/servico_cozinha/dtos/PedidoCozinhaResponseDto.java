package com.cozinha.servico_cozinha.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.cozinha.servico_cozinha.entities.StatusCozinha;

public record PedidoCozinhaResponseDto(         
		Long id,
        StatusCozinha status,
        LocalDateTime dataHora,
        List<ItemPedidoDto> itens) {

}
