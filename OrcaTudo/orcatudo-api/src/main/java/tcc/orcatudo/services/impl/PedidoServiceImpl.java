package tcc.orcatudo.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.entitites.Pedido;
import tcc.orcatudo.repository.CarrinhoRepository;
import tcc.orcatudo.repository.PedidoRepository;
import tcc.orcatudo.services.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public List<Pedido> getPedidoByCarrinhoId(int id) {
        return pedidoRepository.findAllByCarrinhoId(id);
    }

    @Override
    public Pedido postPedido(int idDoCarrinho) {
        Carrinho carrinho = carrinhoRepository.findById(idDoCarrinho)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum carrinho encontrado com id: "+ idDoCarrinho));
        carrinho.setStatus(false);
        Pedido pedido = new Pedido();
        pedido.setCarrinho(carrinho);
        pedido.setData(LocalDateTime.now());
        return pedidoRepository.save(pedido);

    }

    @Override
    public void deletePedidoById(int id) {
        Pedido pedido = pedidoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum pedido com o id: "+ id));
        
        List<Carrinho> carrinhos = carrinhoRepository.findAllByUsuarioId(pedido.getCarrinho().getUsuario().getId());
        List<Carrinho> carrinhosAtivos = carrinhos.stream().filter(Carrinho::isStatus).toList();
        if (carrinhosAtivos.size() > 0) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "O usuário já possui um carrinho ativo, não é possível desfazer pedido");
        }
        pedido.getCarrinho().setStatus(true);
        pedidoRepository.delete(pedido);
    }
    
}
