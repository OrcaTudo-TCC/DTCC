package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.dtos.PostItemCarrinhoDTO;
import tcc.orcatudo.dtos.PutItemCarrinhoDTO;
import tcc.orcatudo.entitites.ItemCarrinho;
import tcc.orcatudo.repository.CarrinhoRepository;
import tcc.orcatudo.repository.ItemCarrinhoRepository;
import tcc.orcatudo.repository.ProdutoRepository;
import tcc.orcatudo.services.ItemCarrinhoService;

public class ItemCarrinhoServiceImpl implements ItemCarrinhoService{

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public List<ItemCarrinho> getItemByCarrinhoId(int id) {
        return itemCarrinhoRepository.findByCarrinhoId(id);
    }

    @Override
    public ItemCarrinho putItemCarrinho(PutItemCarrinhoDTO itemToUpdate) {
        ItemCarrinho updatedItem = ItemCarrinho.fromDTO(itemToUpdate);
        updatedItem.setProduto(produtoRepository.findByNome(itemToUpdate.getNomeDoProduto())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto com nome: "+ itemToUpdate.getNomeDoProduto())));
        updatedItem.setCarrinho(carrinhoRepository.findById(itemToUpdate.getIdDoCarrinho())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum carrinho com id: "+ itemToUpdate.getIdDoCarrinho())));
        return itemCarrinhoRepository.save(updatedItem);
    }

    @Override
    public void postItemCarrinho(PostItemCarrinhoDTO itemToSave) {
        ItemCarrinho updatedItem = ItemCarrinho.fromDTO(itemToSave);
        updatedItem.setProduto(produtoRepository.findByNome(itemToSave.getNomeDoProduto())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto com nome: "+ itemToSave.getNomeDoProduto())));
        updatedItem.setCarrinho(carrinhoRepository.findById(itemToSave.getIdDoCarrinho())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum carrinho com id: "+ itemToSave.getIdDoCarrinho())));
        itemCarrinhoRepository.save(updatedItem);
    }
    
    

}
