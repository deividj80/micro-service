package br.com.deivid.microservice.loja.controller;

import br.com.deivid.microservice.loja.dto.CompraDTO;
import br.com.deivid.microservice.loja.model.Compra;
import br.com.deivid.microservice.loja.service.CompraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compra")
public class CompraController {

    private static final Logger LOG = LoggerFactory.getLogger(CompraController.class);

    @Autowired
    private CompraService compraService;

    @RequestMapping("{id}")
    public Compra getById(@PathVariable("id") Long id){
        return compraService.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Compra realizaCompra(@RequestBody CompraDTO compraDTO){

        LOG.info("Realizando a compra : {}",compraDTO.toString());
        return compraService.realizaCompra(compraDTO);
    }
}
