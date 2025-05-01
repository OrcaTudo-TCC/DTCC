package tcc.orcatudo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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



    @Operation(
        summary = "Cria um carrinho",
        description = "<h3>Cria um carrinho ativo para o Usuario corresponde ao email, caso o usuario já tenha um carrinho ativo lança uma Exeção</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Criou um carrinho ativo com sucesso", content = @Content(schema = @Schema(implementation = Carrinho.class))),
        @ApiResponse(responseCode = "409", description = "O usuário já possui um carrinho ativo, operação não realizada", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário corresponde ao email", content = @Content())
    })
    @PostMapping("/{email}")
    public ResponseEntity<Carrinho> postCarrinho(@Parameter(required = true,description = "Email do Usuário" )@PathVariable String email){
        return ResponseEntity
        .status(HttpStatus.CREATED).body(carrinhoService.postCarrinho(email));
    }


}
