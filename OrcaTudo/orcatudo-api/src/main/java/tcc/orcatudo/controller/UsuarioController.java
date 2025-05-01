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
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuarios" , description = "Operacões relacionadas aos usuários: Usuario e Fornecedor")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;


    @Operation(summary = "Pesquisa de Usuario: por id ou email",  description = "Sem Parâmetros: Retorna uma lista com todos os usuarios cadastrados.<br> || Passando Id ou Email: Retorna o Usuario correspondete ao Id ou Email<br> || Caso passado os dois parâmetros pesquisa por id")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Retornou o Usuário com sucesso.",
            content = @Content(schema = @Schema(implementation = Usuario.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrou o Usuário.", content = @Content())
    })
    @GetMapping()
    public List<Usuario> getUsuarioById(@Parameter(required = false, description = "id do Usuário")@RequestParam(required = false) Integer id ,@Parameter(required = false, description = "Email do Usuário") @RequestParam(required = false) String email){
        if (id != null) {
            return  usuarioService.getUsuarioByID(id);
        }else if (email != null) {
            return usuarioService.getUsuarioByEmail(email);
        }

        return usuarioService.getAllUsuario();
    }

    @Operation(
    summary = "Edite atributos do Usuário correspondente ao Id",
    description = "É necessário passar um Id no caminho da Uri por exemplo: \"/usuario/5\",<br>" +
    "e no corpo da requisição enviar o nome do atributo dois ponto e o novo valor desse atributo, por exemplo: \"nome\":\"Matheus\"",
    requestBody =  @io.swagger.v3.oas.annotations.parameters.RequestBody(
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
        @ApiResponse(responseCode = "200", description = "Atualizou o atributo do usuário com sucesso",
        content = @Content(
            schema = @Schema(implementation = Usuario.class)
        )),
        @ApiResponse(responseCode = "404", description = "Não encontrou o Usuário", content = @Content()),
        @ApiResponse(responseCode = "400", description = "O atributo passado não é válido.", content = @Content())
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(@RequestBody HashMap<String,String> campos ,@Parameter(required = true, description = "id do Usuário") @PathVariable int id){
        return ResponseEntity.ok(usuarioService.putUsuario(campos , id));
    }
    @Operation(summary = "deleta o usuário pelo id", description = "Deleta o usuário correspondente ao id passado pelo Path da URI, exemplo: \"usuarios/7\"")
    @DeleteMapping("/{id}")
    public void deleteUsuario(@Parameter(required = true, description = "id do Usuário", example = "7") @PathVariable int id){
        usuarioService.deleteUsuarioById(id);
    }


}
