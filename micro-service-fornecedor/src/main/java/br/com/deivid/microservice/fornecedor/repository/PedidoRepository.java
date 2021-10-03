package br.com.deivid.microservice.fornecedor.repository;

import br.com.deivid.microservice.fornecedor.model.Pedido;
import org.springframework.data.repository.CrudRepository;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
