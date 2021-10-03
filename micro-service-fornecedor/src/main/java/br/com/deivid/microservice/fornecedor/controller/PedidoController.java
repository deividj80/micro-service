package br.com.deivid.microservice.fornecedor.controller;

import br.com.deivid.microservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.deivid.microservice.fornecedor.model.Pedido;
import br.com.deivid.microservice.fornecedor.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedido")
public class PedidoController {

	private static final Logger LOG = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Pedido realizaPedido(@RequestBody List<ItemDoPedidoDTO> produtos) {

		LOG.info("Realiza o pedido: {}",produtos.toString());

		return pedidoService.realizaPedido(produtos);
	}
	
	@RequestMapping("/{id}")
	public Pedido getPedidoPorId(@PathVariable Long id) {

		LOG.info("Busca o pedido com o id: {}", id);

		return pedidoService.getPedidoPorId(id);
	}
}
