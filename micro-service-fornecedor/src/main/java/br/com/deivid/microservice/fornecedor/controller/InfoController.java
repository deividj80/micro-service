package br.com.deivid.microservice.fornecedor.controller;

import br.com.deivid.microservice.fornecedor.model.InfoFornecedor;
import br.com.deivid.microservice.fornecedor.service.InfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private static final Logger LOG = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;

    @RequestMapping("/{estado}")
    public InfoFornecedor getInfoPorEstado(@PathVariable String estado) {

        LOG.info("Buscando as informações do estado: {}",estado);

        return infoService.getInfoPorEstado(estado);
    }
}