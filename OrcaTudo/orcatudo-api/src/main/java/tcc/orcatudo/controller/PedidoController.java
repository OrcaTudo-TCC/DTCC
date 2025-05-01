package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import tcc.orcatudo.entitites.Pedido;
import tcc.orcatudo.services.PedidoService;

@RestController
@RequestMapping("/pedido")
@Tag( name = "Pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Operation(summary = "Retorna todos os Pedidos de um usuário",description = "</h3>Retorna a lista de Pedidos do usuário correspondente ao id passado pelo caminho da requisição</h3>")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Retornou os pedidos com sucesso",
            content = @Content(schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário correspondente ao id", content = @Content())
    })
    @GetMapping("/{id}")
    public List<Pedido> getPedidoByCarrinhoId(@Parameter(required = true, description = "Id do usuário")@PathVariable int id){
        return pedidoService.getPedidoByCarrinhoId(id);
    }

    @Operation(summary = "Cria um pedido com o Id do carrinho", description = "<h3>Cria um pedido com id de um carrinho, muda o status do carrinho para false(desativado)<br> passado pelo caminho da requisição, Exemplo \"/pedido/5\"</h3>")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Criou o pedido com sucesso",
            content = @Content(schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário correspondente ao id", content = @Content())
    })
    @PostMapping("/{id}")
    public ResponseEntity<Pedido> postPedido(@Parameter(required = true, description = "Id do Carrinho")@PathVariable int id){
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(pedidoService.postPedido(id));
    }

    @Operation(summary = "Deleta um Pedido pelo seu ID", description = "<h3>Deleta um pedido pelo seu id<br> passado pelo caminho da requisição, Exemplo: \"/pedido/8\"</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Deletou o pedido com sucesso", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário correspondente ao id", content = @Content()),
        @ApiResponse(responseCode = "409", description = "O usuário já possui um carrinho ativo, não é possível desfazer pedido")
    })
    @DeleteMapping("/{id}")
    public void deletePedido(@Parameter(required = true, description = "Id do Pedido")@PathVariable int id){
        pedidoService.deletePedidoById(id);
    }


}
