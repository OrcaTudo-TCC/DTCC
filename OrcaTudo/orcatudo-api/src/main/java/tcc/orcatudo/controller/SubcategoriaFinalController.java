package tcc.orcatudo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tcc.orcatudo.dtos.SubcategoriaFinalDTO;
import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.entitites.SubcategoriaFinal;
import tcc.orcatudo.services.SubcategoriaFinalService;

@RestController
@RequestMapping("/subcategoriafinal")
@Tag(name = "Categorias")
public class SubcategoriaFinalController {

    @Autowired
    SubcategoriaFinalService subFinalService;

    @Operation(summary = "Retorna as categorias Finais", description = "<h3>Retorna as categorias finais pelo nome da subcategoria via caminho da requisição<br>Exemplo\"\\subcategoriafinal\\chave-de-fenda\"</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Achou as categorias finais com sucesso", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Não encontrou nenhuma subcategoria com o nome fornecido", content = @Content())
    })
    @GetMapping("/{subcategoria}")
    public List<SubcategoriaFinal> getSubcategoriaFinalBySubcategoria(@Parameter(required = true, description = "Nome da Subcategoria")@PathVariable String subcategoria){
        return subFinalService.getSubcategoriaFinalBySubcategoriaNome(subcategoria);
    }
    //-------------------------------------------------------------------------------------------------
        @Operation(
        summary = "Edita o nome da subcategoria final",
        description = "<h3>Edita a subcategoria final correspondente ao Id passado pelo caminho da requisição<br>o novo nome deve ser passado pelo corpo da requição sem aspas</h3>",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "Novo nome sem aspas",
            content = @Content(schema = @Schema(example = "novo nome"))
        )
    )
    @ApiResponses({
        @ApiResponse( responseCode = "200", description = "Editou o nome da subcategoria final com sucesso", content = @Content(schema = @Schema(implementation = Subcategoria.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Não encontrou nenhuma subcategoria final com o id passado",
            content = @Content()
        )
    })
    @PutMapping("/{id}/nome")
    public ResponseEntity<SubcategoriaFinal> putSubcategoriaFinal(@Parameter(required = true, description = "id da subcategoria final")@PathVariable int id, @RequestBody String novoNome){
        return ResponseEntity.ok(subFinalService.updateNomeSubcategoriaFinal(id , novoNome));
    }
    //-----------------------------------------------------------------------------------------------
    @Operation(
        summary = "Edita a Subcategoria a qual a Subcategoria final pertence",
        description = "<h3>Edita a Subcategoria a qual a Subcategoria final pertence, Por meio do caminho da requisição deverá ser passado o id da Subcategoria final<br> e por do corpo da requisição o id da Subcategoria</h3>",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "<h3>Id da Subcategoria</h3>"
        ))
    @ApiResponses({
        @ApiResponse( responseCode = "200", description = "Editou a Subcategoria da Subcategoria final com sucesso", content = @Content(schema = @Schema(implementation = Subcategoria.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Não encontrou nenhuma Subcategoria final ou Subcategoria com o id passado",
            content = @Content()
        )
    })
    @PutMapping("/{idSubcategoriaFinal}/update_subcategoria")
    public ResponseEntity<SubcategoriaFinal> putSubcategoriaFinal(@Parameter(required = true, description = "Id da Subcategoria final") @PathVariable int idSubcategoriaFinal, @RequestBody int idSubcategoria){
        return ResponseEntity.ok(subFinalService.updateSubcategoria(idSubcategoriaFinal , idSubcategoria));
    }
    //-----------------------------------------------------------------------
    @Operation(
        summary = "Cria uma Subcategoria final",
        description = "<h3>Cria uma Subcategoria final com os atributos passado via corpo da requisição</h3>",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "<h3>Atributos da subcategoria final</h3>",
        content = @Content(schema = @Schema(implementation = SubcategoriaFinalDTO.class))
    ))
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subcategoria final criada com sucesso", content = @Content()),
        @ApiResponse(responseCode = "400", description = "Bad request, tem alguma coisa errada na requisição", content = @Content())
    })
    @PostMapping()
    public ResponseEntity<SubcategoriaFinal> postSubcategoriaFinal(@RequestBody SubcategoriaFinalDTO dto){
        return ResponseEntity.ok(subFinalService.postSubcategoriaFinal(dto));
    }

    @Operation(summary = "Deleta uma Subcategoria final pelo id", description = "<h3>Deleta a subcategoria final correspondente ao id passado via caminho da requisição</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subcategoria final deletada com sucesso", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Nenhuma Subcategoria final corresponde ao id passado", content = @Content())
    })
    @DeleteMapping("/{id}")
    public void deleteSubcategoriaFinal(@Parameter(required = true, description = "Id da Subcategoria final") @PathVariable int id){
        subFinalService.deleteByid(id);
    }
}
