package tcc.orcatudo.services;

import java.io.IOException;
import java.util.List;


import tcc.orcatudo.dtos.PutProdutoDTO;
import tcc.orcatudo.dtos.SaveProdutoDTO;
import tcc.orcatudo.entitites.Produto;

public interface ProdutoService {

    Produto saveProduto(SaveProdutoDTO produto) throws IOException;

    List<Produto> getAllProduto();

    long countAllproduto();

    List<Produto> getProdutoBySubcategoriaFinal(String nome);

    long countProdutoBySubcategoriaFinal(String subcategoriaFinal);

    List<Produto> getProdutoByFornecedor(String nome);

    long countProdutoByFornecedor(String nome);

    Produto getProdutoById(int id);

    Produto getProdutoByNome(String nome);

    Produto putProduto(PutProdutoDTO produto);

    void deleteProdutoById(int id);

    byte[] getImagem(int id);

    List<Produto> getProdutoNomeLike(String nome);

}
