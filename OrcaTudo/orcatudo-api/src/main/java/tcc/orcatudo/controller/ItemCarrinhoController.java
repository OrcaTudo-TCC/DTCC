package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.dtos.PostItemCarrinhoDTO;
import tcc.orcatudo.dtos.PutItemCarrinhoDTO;
import tcc.orcatudo.entitites.ItemCarrinho;
import tcc.orcatudo.services.ItemCarrinhoService;

@RestController
@RequestMapping("/item-carrinho")
public class ItemCarrinhoController {

    @Autowired
    ItemCarrinhoService itemCarrinhoService;

    @GetMapping("/{id}")
    public List<ItemCarrinho> getItemByCarrinhoId(@PathVariable int id){
        return itemCarrinhoService.getItemByCarrinhoId(id);
    }

    @PutMapping()
    public ResponseEntity<ItemCarrinho> putItemCarrinho(@RequestBody PutItemCarrinhoDTO itemToUpdate){
        return ResponseEntity.ok(itemCarrinhoService.putItemCarrinho(itemToUpdate));
    }
    
    @PostMapping()
    public void postItemCarrinho(@RequestBody PostItemCarrinhoDTO postItemCarrinhoDTO){
        itemCarrinhoService.postItemCarrinho(postItemCarrinhoDTO);
    }
}


