package com.cozinha.services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cozinha.dtos.ItemPedidoDto;
import com.cozinha.dtos.PedidoCozinhaRequestDto;
import com.cozinha.dtos.PedidoCozinhaResponseDto;
import com.cozinha.entities.ItemPedidoCozinha;
import com.cozinha.entities.PedidoCozinha;
import com.cozinha.entities.StatusCozinha;
import com.cozinha.repository.PedidoCozinhaRepository;

@Service
public class CozinhaService {

    private final PedidoCozinhaRepository repository;

    public CozinhaService(PedidoCozinhaRepository repository) {
        this.repository = repository;
    }

    public PedidoCozinhaResponseDto receberPedido(PedidoCozinhaRequestDto dto) {
        PedidoCozinha pedido = new PedidoCozinha();
        pedido.setId(dto.id());
        pedido.setClienteId(dto.clienteId());
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(StatusCozinha.AGUARDANDO);

        var itens = dto.itens().stream().map(i -> {
            ItemPedidoCozinha item = new ItemPedidoCozinha();
            item.setNome(i.nome());
            item.setQuantidade(i.quantidade());
            item.setPedido(pedido);
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itens);

        return toDto(repository.save(pedido));
    }

    public PedidoCozinhaResponseDto iniciarPreparo(Long id) {
        PedidoCozinha pedido = buscar(id);
        pedido.setStatus(StatusCozinha.EM_PREPARO);
        return toDto(repository.save(pedido));
    }

    public PedidoCozinhaResponseDto finalizarPedido(Long id) {
        PedidoCozinha pedido = buscar(id);
        pedido.setStatus(StatusCozinha.PRONTO);
        return toDto(repository.save(pedido));
    }

    private PedidoCozinha buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    private PedidoCozinhaResponseDto toDto(PedidoCozinha pedido) {
        var itens = pedido.getItens().stream()
                .map(i -> new ItemPedidoDto(i.getNome(), i.getQuantidade()))
                .toList();

        return new PedidoCozinhaResponseDto(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getDataHora(),
                itens
        );
    }
}
