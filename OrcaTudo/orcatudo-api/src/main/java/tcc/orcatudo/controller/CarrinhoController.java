package tcc.orcatudo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import tcc.orcatudo.dtos.PostCarrinhoDTO;
import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.services.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
@Tag(name = "Carrinho")
public class CarrinhoController {

    @Autowired
    CarrinhoService carrinhoService;

    @Operation(summary = "Retorna um carrinho", description = "<h3>Retorna o carrinho correspondente ao id do usuário passado pelo caminho da requisição<br>Exemplo: \"/carrinho/7 \"</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retornou com sucesso o carrinho", content = @Content(schema = @Schema(implementation = Carrinho.class))),
        @ApiResponse(responseCode = "404", description = "Nenhuma correspondência ao id do Usuário passado", content = @Content())
    })
    @GetMapping("/{idUsuario}")
    public Carrinho getCarrinhoByUsuarioId(@Parameter(required = true, description = "Id do Usuário")@PathVariable int idUsuario){
        return carrinhoService.getCarrinhoByUsuarioId(idUsuario);
    }

    @Operation(summary = "Atualiza o Status do carrinho", description = "Atualiza o status do carrinho correspondente ao usuário")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Atualizou com sucesso o carrinho", content = @Content(schema = @Schema(implementation = Carrinho.class))),
        @ApiResponse(responseCode = "404", description = "Nenhuma correspondência ao id do Usuário passado", content = @Content())
    })
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Carrinho> putCarrinhoById(@Parameter(required = true, description = "Id do Usuário")@PathVariable int idUsuario, @RequestParam boolean status){
        return ResponseEntity.ok(carrinhoService.changeCarrinhoStatus(idUsuario ,status));
    }

    @Operation(
        summary = "Cria um carrinho",
        description = "Cria um carrinho com os atributos passados via corpo da requisição",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Atributos necessários para criar carrinho",
            content = @Content(schema = @Schema(implementation = PostCarrinhoDTO.class))
        ))
    @PostMapping()
    public ResponseEntity<Carrinho> postCarrinho(@RequestBody PostCarrinhoDTO carrinhoDTO){
        return ResponseEntity
        .status(HttpStatus.CREATED).body(carrinhoService.postCarrinho(carrinhoDTO));
    }


}
