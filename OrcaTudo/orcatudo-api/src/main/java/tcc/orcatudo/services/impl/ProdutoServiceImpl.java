package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.dtos.PutProdutoDTO;
import tcc.orcatudo.dtos.SaveProdutoDTO;
import tcc.orcatudo.entitites.Produto;
import tcc.orcatudo.repository.FornecedorRepository;
import tcc.orcatudo.repository.ProdutoRepository;
import tcc.orcatudo.repository.SubcategoriaFinalRepository;
import tcc.orcatudo.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    SubcategoriaFinalRepository subcategoriaFinalRepository;

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
    public Produto putProduto(PutProdutoDTO produto) {
        if (!produtoRepository.existsById(produto.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "Produto não encontrado para atualizar");
        }
        Produto updatedProduto = produtoRepository.findById(produto.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum produto encontrado com id: "+ produto.getId()));
        updatedProduto.setNome(produto.getNome());
        updatedProduto.setDescricao(produto.getDescricao());
        updatedProduto.setPreco(produto.getPreco());
        updatedProduto.setImagem(produto.getImagem());
        updatedProduto.setFornecedor(fornecedorRepository.findByNome(produto.getNomeDoFornecedor())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum fornecedor com nome: "+ produto.getNomeDoFornecedor())));
        updatedProduto.setSubcategoriaFinal(subcategoriaFinalRepository.findByNome(produto.getNomeDasubcategoriaFinal())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma subcategoria fianl com nome: "+ produto.getNomeDasubcategoriaFinal())));

        return produtoRepository.save(updatedProduto);
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
    public Produto saveProduto(SaveProdutoDTO produto) {
        Produto produtoToSave = Produto.fromDTO(produto);
        produtoToSave.setFornecedor(fornecedorRepository.findByNome(produto.getNomeDoFornecedor())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum fornecedor com nome: "+ produto.getNomeDoFornecedor())));
        produtoToSave.setSubcategoriaFinal(subcategoriaFinalRepository.findByNome(produto.getNomeDasubcategoriaFinal())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma subcategoria fianl com nome: "+ produto.getNomeDasubcategoriaFinal())));
        return produtoRepository.save(produtoToSave);
    }

    

}
