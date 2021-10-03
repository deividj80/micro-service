package br.com.deivid.microservice.loja.service;

import br.com.deivid.microservice.loja.client.FornecedorClient;
import br.com.deivid.microservice.loja.client.TransportadorClient;
import br.com.deivid.microservice.loja.dto.*;
import br.com.deivid.microservice.loja.model.Compra;
import br.com.deivid.microservice.loja.model.CompraState;
import br.com.deivid.microservice.loja.repository.CompraRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);

    @Autowired
    private FornecedorClient fornecedorClient;

    @Autowired
    private TransportadorClient transportadorClient;

    @Autowired
    private CompraRepository compraRepository;

    @HystrixCommand( threadPoolKey ="getByIdThreadPool" )
    public Compra getById(Long id) {
        return compraRepository.findById(id).orElse(new Compra());
    }

    @HystrixCommand( fallbackMethod = "realizaCompraFallback" ,threadPoolKey = "realizaCompraThreadPool")
    public Compra realizaCompra(CompraDTO compraDTO) {

        Compra compraSalva = new Compra();
        compraSalva.setEnderecoDestino(compraDTO.getEndereco().toString());
        compraSalva.setState(CompraState.RECEBIDO);
        compraSalva = compraRepository.save(compraSalva);
        compraDTO.setCompraId(compraSalva.getId());

        LOG.info("Buscando informações do fornecedor {}", compraDTO.getEndereco().getEstado());

        InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(compraDTO.getEndereco().getEstado());
        InfoPedidoDTO pedido = fornecedorClient.realizaPedido(compraDTO.getItens());
        compraSalva.setPedidoId(pedido.getId());
        compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());
        compraSalva.setState(CompraState.PEDIDO_REALIZADO);
        compraSalva = compraRepository.save(compraSalva);

        LOG.info("Realizando um pedido!");

        InfoEntragaDTO entregaDTO = new InfoEntragaDTO();
        entregaDTO.setPedidoId(pedido.getId());
        entregaDTO.setDataParaEntrega(LocalDate.now().plusDays(pedido.getTempoDePreparo()));
        entregaDTO.setEnderecoOrigem(info.getEndereco());
        entregaDTO.setEnderecoDestino(compraDTO.getEndereco().toString());
        VoucherDTO voucher = transportadorClient.reservaEntrega(entregaDTO);
        compraSalva.setState(CompraState.RESERVA_ENTREGA_REALIZADA);
        compraSalva.setDataParaEntrega(voucher.getPrevisaoParaEntrega());
        compraSalva.setVoucher(voucher.getNumero());
        compraSalva = compraRepository.save(compraSalva);

        return compraSalva;
    }

    public Compra realizaCompraFallback(CompraDTO compraDTO){

        if (compraDTO.getCompraId() != null){
            return compraRepository.findById(compraDTO.getCompraId()).orElse(new Compra());
        }

        Compra compraFallback = new Compra();
        compraFallback.setEnderecoDestino(compraDTO.getEndereco().toString());
        return compraFallback;

    }

}
