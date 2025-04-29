package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import tcc.orcatudo.dtos.OperacaoDTO;
import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.StatusEnum;
import tcc.orcatudo.services.OperacaoService;

@RestController
@RequestMapping("/operacao")
@Tag(name = "Operação")
public class OperacaoController {

    @Autowired
    OperacaoService operacaoService;

    @Operation(summary = "Retorna as operações correspondentes a um Usuario", description = "<h3>Retorna uma lista de operações realizadas por um Usuario com o id passado pelo caminho da requisição<br>Exemplo: \"/operacao/3\"</h3>")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Retornou com sucesso a lista de operações",
            content = @Content(schema = @Schema(implementation = Operacao.class))),
        @ApiResponse(responseCode = "404", description = "Nenhum usuário corresponde ao id passsado", content = @Content())
    })
    @GetMapping("/{id}")
    public List<Operacao> getOperacaoByUsuario(@Parameter(required = true, description = "Id do Usuário")@PathVariable int id){
        return operacaoService.getOperacaoByUsuarioId(id);
    }

    @Operation(summary = "Atualiza o Status da Operação", description = "<h3>Atualiza o status da operação correspondente ao id da operação passado via caminho da requisição, o status deve ser definido pelo corpo da requisição</h3>")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Atualizou com sucesso o status da operacao",
            content = @Content(schema = @Schema(implementation = Operacao.class))),
        @ApiResponse(responseCode = "404", description = "Nenhuma Operação corresponde ao id passsado", content = @Content())
    })
    @PutMapping("/{idDaOperacao}")
    public ResponseEntity<Operacao> putStatusByOperacaoId(@Parameter(required = true, description = "Id da Operação")@PathVariable int idDaOperacao ,@RequestBody StatusEnum status){
        return ResponseEntity.ok(operacaoService.putOperacaoStatusById(idDaOperacao , status));
    }

    @Operation(
        summary = "Cria uma Operacao",
        description = "Cria uma Operação com base nos atributos passados via corpo da requisição",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Atributos da Operação",
            content = @Content(schema = @Schema(implementation = OperacaoDTO.class))
        ))
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Criou uma oOperação com sucesso",
            content = @Content(schema = @Schema(implementation = Operacao.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request, há algo errado na requisição", content = @Content())
    })
    @PostMapping()
    public ResponseEntity<Operacao> postOperacaoById(@RequestBody OperacaoDTO dto){
        return ResponseEntity.ok(operacaoService.postOperacaoById(dto));
    }

}
