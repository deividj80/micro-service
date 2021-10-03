package br.com.deivid.microservice.loja.client;

import br.com.deivid.microservice.loja.dto.InfoFornecedorDTO;
import br.com.deivid.microservice.loja.dto.ItemDaCompraDTO;
import br.com.deivid.microservice.loja.dto.InfoPedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("fornecedor")
public interface FornecedorClient {

    @RequestMapping("/info/{estado}")
    InfoFornecedorDTO getInfoPorEstado(@PathVariable String estado);

    @RequestMapping(method = RequestMethod.GET, value = "/pedido")
    InfoPedidoDTO realizaPedido(List<ItemDaCompraDTO> itens);

}
