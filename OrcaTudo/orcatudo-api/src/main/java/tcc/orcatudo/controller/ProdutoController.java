package tcc.orcatudo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tcc.orcatudo.dtos.PutProdutoDTO;
import tcc.orcatudo.dtos.SaveProdutoDTO;
import tcc.orcatudo.entitites.Produto;
import tcc.orcatudo.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService; 

    @Operation(summary = "Retorna o Produto por Nome ou Id", description = "<h3>Retorna o produto correspondente ao Nome ou ao Id, caso nenhum dos dois seja passado retorna uma lista com todos os produtos<br>Exemplo de requisição: \"\\produtos\\4\"</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retornou o(s) produto(s) com sucesso"),
        @ApiResponse(responseCode = "404", description = "Id ou Nome não possui correspondência", content = @Content())
    })
    @GetMapping()
    public List<Produto> getProduto(@Parameter(required = false, description = "Id do produto")@RequestParam(required = false) Integer id ,@Parameter(required = true, description = "Nome do produto") @RequestParam(required =  false) String nome ){
        if (id != null) {
            return List.of(produtoService.getProdutoById(id));
        }else if(nome != null){
            return List.of(produtoService.getProdutoByNome(nome));
        }
        return produtoService.getAllProduto();
    }

    @Operation(summary = "Retorna lista de produtos correspondete a subcategoria final", description = "<h3>Retorna uma lista com todos os produtos da subcategoria passada pelo caminho da requisição<br>Exemplo:\"\\produtos\\subcategoriafinal\\chaves_de_fenda\"</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retornou a lista de produtos com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nome da Subcategoria não possui correspondência, não encontrado", content = @Content())
    })
    @GetMapping("/subcategoriafinal/{nome}")
    public List<Produto> getProdutoBySubcategoriaFinal(@Parameter(required = true, description = "Nome da Subcategoria final")@PathVariable String nome){
        return produtoService.getProdutoBySubcategoriaFinal(nome);
    }

    @Operation(summary = "Retorna lista de produtos correspondente a um fornecedor", description = "<h3>Retorna uma lista com todos os produtos de um forneceodr passado pelo caminho da requisição<br>Exemplo:\"produtos\\fornecedor\\casa_dos_pregos\"</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retornou a lista de produtos com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nome da Subcategoria não possui correspondência, não encontrado", content = @Content())
    })
    @GetMapping("/fornecedor/{nome}")
    public List<Produto> getProdutoByFornecedor(@Parameter(required = true,description = "Nome do Fornecedor")@PathVariable String nome){
        return produtoService.getProdutoByFornecedor(nome);
    }


    @GetMapping("{id}/imagem")
    public byte[] getImagem(@PathVariable int id) throws IOException{
        return produtoService.getImagem(id);
    }

    @Operation(summary = "Atualiza os atributos de um produto", description = "<h3>Atualiza um produto conforme o valro dos atributos passado via corpo da requisição.</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Atualizou o produto ocm sucesso"),
        @ApiResponse(responseCode = "404", description = "Id do produto não possui correspondência", content = @Content()),
        @ApiResponse(responseCode = "400", description = "Bad Resquest: Há algo errado na requisição", content = @Content())
    })
    @PutMapping()
    public ResponseEntity<Produto> putProduto(@RequestBody PutProdutoDTO produto){
        return ResponseEntity.ok(produtoService.putProduto(produto));
    }

    @Operation(summary = "Cria um Produto", description = "<h3>Cria um Produto conforme o valor dos atributos no corpo da requisição</h3>")
    @PostMapping()
    public ResponseEntity<Produto> saveProduto(@RequestBody SaveProdutoDTO produto) throws IOException{
        return ResponseEntity.ok(produtoService.saveProduto(produto));
    }

    @Operation(summary = "Deleta um produto pelo ID", description = "Deleta o produto correspondente ao Id passado pelo caminho da requisição")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deletou o Produto com sucesso", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Nome da Subcategoria final não possui correspondência, não encontrado", content = @Content())
    })
    @DeleteMapping("/{id}")
    public void deleteProduto(@Parameter(required = true, description = "Id do produto")@PathVariable int id){
        produtoService.deleteProdutoById(id);
    }
    

}
