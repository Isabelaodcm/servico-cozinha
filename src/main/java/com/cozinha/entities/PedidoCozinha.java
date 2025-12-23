package com.cozinha.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido", schema = "cozinha")
public class PedidoCozinha {

    @Id
    private Long id; // mesmo ID vindo do serviço de pedidos

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCozinha status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoCozinha> itens;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public StatusCozinha getStatus() {
		return status;
	}

	public void setStatus(StatusCozinha status) {
		this.status = status;
	}

	public List<ItemPedidoCozinha> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoCozinha> itens) {
		this.itens = itens;
	}

}

