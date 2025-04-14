package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Produto;
import tcc.orcatudo.repository.ProdutoRepository;
import tcc.orcatudo.services.ProdutoService;

public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public List<Produto> getAllProduto() {
        return produtoRepository.findAll();
    }

    @Override
    public long countAllproduto() {
        return produtoRepository.count();
    }

    @Override
    public List<Produto> getProdutoBySubcategoriaFinal(String nome) {
        return produtoRepository.findBySubcategoriaFinalNome(nome);
    }

    @Override
    public long countProdutoBySubcategoriaFinal(String subcategoriaFinal) {
        return produtoRepository.countBySubcategoriaFinalNome(subcategoriaFinal);
    }

    @Override
    public Produto getProdutoById(int id) {
        return produtoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Produto não encontrado pelo id"));
    }

    @Override
    public Produto getProdutoByNome(String nome) {
        return produtoRepository.findByNome(nome).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "Produto não encontrado pelo nome"));
    }

    @Override
    public Produto putProduto(Produto produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Produto não encontrado para atualizar");
        }
        return produtoRepository.save(produto);
    }

    @Override
    public void deleteProdutoById(int id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Produto não encontrado para deleção");
        }
        produtoRepository.deleteById(id);
    }

    @Override
    public long countProdutoByFornecedor(String nome) {
        return produtoRepository.countByFornecedorNome(nome);
    }

    @Override
    public List<Produto> getProdutoByFornecedor(String nome) {
        return produtoRepository.findByFornecedorNome(nome);
    }

    @Override
    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    

}
