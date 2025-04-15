package tcc.orcatudo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Pedido;
import tcc.orcatudo.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping("/{id}")
    public Pedido getPedidoByCarrinhoId(@PathVariable int id){
        return pedidoService.getPedidoByCarrinhoId(id);
    }
    @PostMapping()
    public ResponseEntity<Pedido> postPedido(@RequestBody int idDoCarrinho){
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(pedidoService.postPedido(idDoCarrinho));
    }
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable int id){
        pedidoService.deletePedidoById(id);
    }


}
