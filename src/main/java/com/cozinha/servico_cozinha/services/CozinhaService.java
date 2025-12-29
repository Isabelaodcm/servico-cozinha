package com.cozinha.servico_cozinha.services;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cozinha.servico_cozinha.dtos.ItemPedidoDto;
import com.cozinha.servico_cozinha.dtos.PedidoCozinhaRequestDto;
import com.cozinha.servico_cozinha.dtos.PedidoCozinhaResponseDto;
import com.cozinha.servico_cozinha.entities.ItemPedidoCozinha;
import com.cozinha.servico_cozinha.entities.PedidoCozinha;
import com.cozinha.servico_cozinha.entities.StatusCozinha;
import com.cozinha.servico_cozinha.repository.PedidoCozinhaRepository;

@Service
public class CozinhaService {

    private final PedidoCozinhaRepository repository;

    public CozinhaService(PedidoCozinhaRepository repository) {
        this.repository = repository;
    }

    public PedidoCozinhaResponseDto receberPedido(PedidoCozinhaRequestDto dto) {
        PedidoCozinha pedido = new PedidoCozinha();
//        pedido.setId(dto.id());
        System.out.println("id do dto: " + dto.id());
        pedido.setPedidoId(dto.id());
        pedido.setClienteId(dto.clienteId());
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(StatusCozinha.RECEBIDO);

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
        
        PedidoCozinha pedidoSalvo = repository.save(pedido);
        System.out.println("novo status: " + pedidoSalvo.getStatus());
        return toDto(pedidoSalvo);
    }

    public PedidoCozinhaResponseDto finalizarPedido(Long id) {
        PedidoCozinha pedido = buscar(id);
        pedido.setStatus(StatusCozinha.PRONTO);
        
        PedidoCozinha pedidoSalvo = repository.save(pedido);
        System.out.println("novo status: " + pedidoSalvo.getStatus());
        
        return toDto(pedidoSalvo);
    }

    private PedidoCozinha buscar(Long id) {
        return repository.findByPedidoId(id)
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
