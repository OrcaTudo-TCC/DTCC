package tcc.orcatudo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.dtos.PostCarrinhoDTO;
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

    @PutMapping("/{id}")
    public ResponseEntity<Carrinho> putCarrinhoById(@PathVariable int id, @RequestParam boolean status){
        return ResponseEntity.ok(carrinhoService.changeCarrinhoStatus(id ,status));
    }

    @PostMapping()
    public ResponseEntity<Carrinho> postCarrinho(@RequestBody PostCarrinhoDTO carrinhoDTO){
        return ResponseEntity
        .status(HttpStatus.CREATED).body(carrinhoService.postCarrinho(carrinhoDTO));
    }


}
