package tcc.orcatudo.services;

import tcc.orcatudo.entitites.Pedido;

public interface PedidoService {

    Pedido getPedidoByCarrinhoId(int id);

    Pedido postPedido(int idDoCarrinho);

    void deletePedidoById(int id);

}
