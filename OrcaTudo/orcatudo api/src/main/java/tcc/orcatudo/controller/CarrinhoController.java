package tcc.orcatudo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.services.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @GetMapping("/{id}")
    public Carrinho getCarrinhoByUsuarioId(@PathVariable int id){
        return carrinhoService.getCarrinhoByUsuarioId(id);
    }

}
