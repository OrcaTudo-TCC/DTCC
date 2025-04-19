package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.entitites.Pedido;

public interface PedidoService {

    List<Pedido> getPedidoByCarrinhoId(int id);

    Pedido postPedido(int idDoCarrinho);

    void deletePedidoById(int id);

}
