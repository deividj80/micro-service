package br.com.deivid.microservice.fornecedor.controller;

import br.com.deivid.microservice.fornecedor.model.Produto;
import br.com.deivid.microservice.fornecedor.service.ProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping("/{estado}")
	public List<Produto> getProdutosPorEstado(@PathVariable("estado") String estado) {

		LOG.info("Buscando a lista de produtos por estado: {}",estado);

		return produtoService.getProdutosPorEstado(estado);
	}
}
