package tcc.orcatudo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
import tcc.orcatudo.entitites.Fornecedor;
import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.services.FornecedorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/fornecedor")
@Tag(name = "Usuarios")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @Operation(
        summary = "Pesquisa de Fornecedor: por id ou email",
        description = "Sem Parâmetros: Retorna uma lista com todos os fornecedores cadastrados.<br>|| Passando Id ou Email: Retorna o fornecedor correspondete ao Id ou Email<br> || Caso passado os dois parâmetros pesquisa por id"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Retornou o Fornecedor com sucesso", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Não encontrou o Fornecedor.", content = @Content())
    }
    )
    @GetMapping()
    public List<Fornecedor> getFornecedor(@Parameter(required = false, description = "Email do Fornecedor")@RequestParam( required = false) String email,@Parameter(required = false, description = "id do Usuário")@RequestParam( required = false) Integer id){
        if (id != null) {
            return List.of(fornecedorService.getFornecedorById(id));
        }else if (email != null) {
            return List.of(fornecedorService.getFornecedorByEmail(email));
        }else{
            return fornecedorService.getAllFornecedor();
        }
    }
    @Operation(
        summary = "Edite atributos do Fornecedor correspondente ao Id",
        description = "É necessário passar um Id no caminho da Uri por exemplo: \"/usuario/5\",<br>" +
    "e no corpo da requisição enviar o nome do atributo dois ponto e o novo valor desse atributo, por exemplo: \"nome\":\"Matheus\"",
        requestBody = @RequestBody(
            required = true,
            description = "recebe um Map<String,String> ou seja: \"nome do atributo\":\"novo valor\"",
            content = @Content(
            schema = @Schema(
                    type = "object",
                    example = "{ \"nomeDoAtributo\": \"novoValor\" }"
                )
        )
        ))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Atualizou o atributo do Fornecedor com sucesso.",
        content = @Content(
            schema = @Schema(implementation = Usuario.class)
        )),
        @ApiResponse(responseCode = "404", description = "Não encontrou o Fornecedor.", content = @Content()),
        @ApiResponse(responseCode = "400", description = "O atributo passado não é válido.", content = @Content())
    })
    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> putFornecedor(@Parameter(required = true, description = "id do Usuário") @PathVariable int id , @RequestBody HashMap<String , String> campos){
        return  ResponseEntity.ok(fornecedorService.updateFornecedor(campos, id));
    }

    @Operation(summary = "deleta o fornecedor pelo id", description = "Deleta o fornecedor correspondente ao id passado pelo Path da URI, exemplo: \"fornecedor/3\"")
    @DeleteMapping("/{id}")
    public void deleteFornecedor(@Parameter(required = true, description = "id do Usuário", example = "7") @PathVariable int id){
        fornecedorService.deleteFornecedorByID(id);
    }

}
