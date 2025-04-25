package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import tcc.orcatudo.dtos.PostItemCarrinhoDTO;
import tcc.orcatudo.dtos.PutItemCarrinhoDTO;
import tcc.orcatudo.entitites.ItemCarrinho;
import tcc.orcatudo.services.ItemCarrinhoService;

@RestController
@RequestMapping("/item-carrinho")
@Tag( name = "Carrinho", description = "Operações do carrinho e relacionados")
public class ItemCarrinhoController {

    @Autowired
    ItemCarrinhoService itemCarrinhoService;

    @Operation(summary = "Retorna os Itens de um carrinho", description = "<h3>Retorna Uma lista de Itens correspondente ao id do carrinho passado via caminho da requisição.<br>Exemplo: \" /item-carrinho/7 \"</h3>")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Retornou os itens com sucesso",
            content = @Content(schema = @Schema(implementation = ItemCarrinho.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "O id do carrinho não possui correspondência",
            content = @Content()
        )
    })
    @GetMapping("/{id}")
    public List<ItemCarrinho> getItemByCarrinhoId(@Parameter(required = true, description = "Id do Carrinho")@PathVariable int id){
        return itemCarrinhoService.getItemByCarrinhoId(id);
    }

    @Operation(
        summary = "Atualiza um Item-Carrinho",
        description = "<h3>Atualiza um Item-Carrinho com os atributos passados pelo corpo da requisição</h3>",
        requestBody = @RequestBody(
            required = true,
            description = "Atributos necessários para utualizar item",
            content = @Content(schema = @Schema(implementation = PutItemCarrinhoDTO.class))
        ))
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Atualizou o Item com sucesso",
            content = @Content(schema = @Schema(implementation = ItemCarrinho.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "O id do Item ou do Carrinho não possui correspondência",
            content = @Content()
        )
    })
    @PutMapping()
    public ResponseEntity<ItemCarrinho> putItemCarrinho(@RequestBody PutItemCarrinhoDTO itemToUpdate){
        return ResponseEntity.ok(itemCarrinhoService.putItemCarrinho(itemToUpdate));
    }
    

    @Operation(summary = "Cria um Item-carrinho",description = "<h3>Cria um Item-carrinho com os atributos passados via corpo da requisição</h3>")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Criou um Item-Carrinho com sucesso",
            content = @Content()
        ),
        @ApiResponse(
            responseCode = "404",
            description = "O id do Carrinho não possui correspondência",
            content = @Content()
        )
    })
    @PostMapping()
    public void postItemCarrinho(@RequestBody PostItemCarrinhoDTO postItemCarrinhoDTO){
        itemCarrinhoService.postItemCarrinho(postItemCarrinhoDTO);
    }
}


