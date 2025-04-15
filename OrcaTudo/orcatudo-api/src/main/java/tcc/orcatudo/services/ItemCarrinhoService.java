package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.dtos.PostItemCarrinhoDTO;
import tcc.orcatudo.dtos.PutItemCarrinhoDTO;
import tcc.orcatudo.entitites.ItemCarrinho;

public interface ItemCarrinhoService {

    List<ItemCarrinho> getItemByCarrinhoId(int id);

    ItemCarrinho putItemCarrinho(PutItemCarrinhoDTO itemToUpdate);

    void postItemCarrinho(PostItemCarrinhoDTO itemToSave);
    
}
    
    