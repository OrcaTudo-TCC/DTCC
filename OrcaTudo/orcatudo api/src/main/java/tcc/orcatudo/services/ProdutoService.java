package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.entitites.Produto;

public interface ProdutoService {

    Produto saveProduto(Produto produto);

    List<Produto> getAllProduto();

    long countAllproduto();

    List<Produto> getProdutoBySubcategoriaFinal(String nome);

    long countProdutoBySubcategoriaFinal(String subcategoriaFinal);

    List<Produto> getProdutoByFornecedor(String nome);

    long countProdutoByFornecedor(String nome);

    Produto getProdutoById(int id);

    Produto getProdutoByNome(String nome);

    Produto putProduto(Produto produto);

    void deleteProdutoById(int id);

}
