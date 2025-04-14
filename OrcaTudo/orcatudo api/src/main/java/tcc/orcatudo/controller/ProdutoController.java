package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Produto;
import tcc.orcatudo.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService; 

    @GetMapping()
    public List<Produto> getProduto(@RequestParam(required = false) Integer id , @RequestParam(required =  false) String nome ){
        if (id != null) {
            return List.of(produtoService.getProdutoById(id));
        }else if(nome != null){
            return List.of(produtoService.getProdutoByNome(nome));
        }
        return produtoService.getAllProduto();
    }

    @GetMapping("/categoria/{nome}")
    public List<Produto> getProdutoBySubcategoriaFinal(@PathVariable String nome){
        return produtoService.getProdutoBySubcategoriaFinal(nome);

    }

    @GetMapping("/fornecedor/{nome}")
    public List<Produto> getProdutoByFornecedor(@PathVariable String nome){
        return produtoService.getProdutoByFornecedor(nome);
    }

    @PutMapping()
    public ResponseEntity<Produto> putProduto(Produto produto){
        return ResponseEntity.ok(produtoService.putProduto(produto));
    }

    @PostMapping()
    public ResponseEntity<Produto> saveProduto(Produto produto){
        return ResponseEntity.ok(produtoService.putProduto(produto));
    }
}
