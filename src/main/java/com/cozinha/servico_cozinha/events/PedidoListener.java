package com.cozinha.servico_cozinha.events;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cozinha.servico_cozinha.config.RabbitMQConfig;
import com.cozinha.servico_cozinha.services.CozinhaService;

@Component
public class PedidoListener {

    private final Queue cozinhaQueue;

    private final CozinhaService service;

    public PedidoListener(CozinhaService service, Queue cozinhaQueue) {
        this.service = service;
        this.cozinhaQueue = cozinhaQueue;
    }

//    @RabbitListener(queues = RabbitMQConfig.QUEUE_COZINHA)
//    public void receber(PedidoStatusEvent event) {
//
//        switch (event.status()) {
//            case "EM_PREPARO" ->
//                service.iniciarPreparo(event.pedidoId());
//
//            case "PRONTO" ->
//                service.finalizarPedido(event.pedidoId());
//        }
//    }
    
    @RabbitListener(queues = RabbitMQConfig.QUEUE_COZINHA)
    public void receber(PedidoStatusEvent event) {
    	
    	System.out.println("status: " + event.status());
    	System.out.println("Mensagem recebida do RabbitMQ: " + event);
    	try {
        switch (event.status()) {

            case "RECEBIDO" -> {
                service.criarSeNaoExistir(event.pedidoId(), event.clienteId());
            }

            case "EM_PREPARO" -> {
                service.iniciarPreparo(event.pedidoId());
            }

            case "PRONTO" -> {
                service.finalizarPedido(event.pedidoId());
            }
        }
    	} catch (RuntimeException e) {
            System.out.println("Pedido ainda não existe, ignorando evento");
        }
    	
    }

}

