package com.cozinha.servico_cozinha.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "item_pedido", schema = "cozinha")
public class ItemPedidoCozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoCozinha pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public PedidoCozinha getPedido() {
		return pedido;
	}

	public void setPedido(PedidoCozinha pedido) {
		this.pedido = pedido;
	}

}

