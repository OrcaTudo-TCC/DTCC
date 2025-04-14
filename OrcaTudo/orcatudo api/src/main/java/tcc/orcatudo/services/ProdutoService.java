package tcc.orcatudo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import tcc.orcatudo.dtos.PutProdutoDTO;
import tcc.orcatudo.dtos.SaveProdutoDTO;
import tcc.orcatudo.entitites.Produto;

public interface ProdutoService {

    Produto saveProduto(SaveProdutoDTO produto);

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

}
