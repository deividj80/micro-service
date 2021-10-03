package br.com.deivid.microservice.loja.client;

import br.com.deivid.microservice.loja.dto.InfoEntragaDTO;
import br.com.deivid.microservice.loja.dto.VoucherDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("transportador")
public interface TransportadorClient {

    @RequestMapping(path = "/entrega", method = RequestMethod.POST)
    public VoucherDTO reservaEntrega( InfoEntragaDTO pedidoDTO);

}
