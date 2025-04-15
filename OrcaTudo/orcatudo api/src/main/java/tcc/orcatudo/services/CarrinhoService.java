package tcc.orcatudo.services;

import tcc.orcatudo.dtos.PostCarrinhoDTO;
import tcc.orcatudo.entitites.Carrinho;

public interface CarrinhoService {

    Carrinho getCarrinhoByUsuarioId(int id);

    Carrinho postCarrinho(PostCarrinhoDTO carrinhoDTO);

    Carrinho changeCarrinhoStatus(int id ,boolean status);

}
