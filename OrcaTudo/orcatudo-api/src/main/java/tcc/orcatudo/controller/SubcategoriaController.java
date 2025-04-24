package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import tcc.orcatudo.dtos.SubcategoriaDTO;
import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.services.SubcategoriaService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/subcategoria")
@Tag(name = "Categorias")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    @Operation(summary = "Retorna as subcategorias da categoria fornecida", description = "<h3>Retorna todas as subcategorias da categoria fornecida pelo caminho da requisição<br>Exemplo: \"\\subcategoria\\ferramentas\"</h3>")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Achou as subcategorias com sucesso",
            content = @Content(
                schema = @Schema(implementation = Subcategoria.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Nome da Categoria não possui correspondência",
            content = @Content())
    })
    @GetMapping("/{categoria}")
    public List<Subcategoria> getSubcategoriaByCategoria(@Parameter(required = true, description = "Nome da categoria")  @PathVariable(required = true) String categoria){
        return subcategoriaService.getByCategoria(categoria);
    }

    @Operation(
        summary = "Edita o nome da subcategoria",
        description = "<h3>Edita a subcategoria correspondente ao Id passado pelo caminho da requisição<br>o novo nome deve ser passado pelo corpo da requição sem aspas</h3>",
        requestBody = @RequestBody(
            required = true,
            description = "Novo nome sem aspas",
            content = @Content(schema = @Schema(example = "novo nome"))
        )
    )
    @ApiResponses({
        @ApiResponse( responseCode = "200", description = "Editou o nome da subcategoria com sucesso", content = @Content(schema = @Schema(implementation = Subcategoria.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Não encontrou nenhuma subcategoria com o id passado",
            content = @Content()
        )
    })
    @PutMapping("/{id}/nome")
    public ResponseEntity<Subcategoria> putSubcategoriaNome(@Parameter(required = true,description = "Novo nome da subcategoria")@PathVariable int id, @RequestBody String novoNome){
        return ResponseEntity.ok(subcategoriaService.updateNome(id , novoNome));
    }

    @Operation(
        summary = "Edita a Categoria a qual a Subcategoria pertence",
        description = "<h3>Edita a Categoria a qual a Subcategoria pertence, Por meio do caminho da requisição deverá ser passado o id da Subcategoria<br> e por do corpo da requisição o id Da Categoria</h3>",
        requestBody = @RequestBody(
            required = true,
            description = "<h3>Id da Categoria</h3>"
        ))
    @ApiResponses({
        @ApiResponse( responseCode = "200", description = "Editou a Categoria da Subcategoria com sucesso", content = @Content(schema = @Schema(implementation = Subcategoria.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Não encontrou nenhuma Subcategoria ou Categoria com o id passado",
            content = @Content()
        )
    })
    @PutMapping("/{id}/categoria")
    public ResponseEntity<Subcategoria> putSubcategoriaCategoria(@Parameter(required = true, description = "id da Subcategoria")@PathVariable(required = true) int id, @RequestBody int idDaCategoria){
        return ResponseEntity.ok(subcategoriaService.updateCategoria(id , idDaCategoria));
    }


    @Operation(
        summary = "Cria uma Subcategoria",
        description = "<h3>Cria uma Subcategoria com os atributos passado via corpo da requisição</h3>",
        requestBody = @RequestBody(
            required = true,
            description = "<h3>Atributos da subcategoria</h3>",
            content = @Content(schema = @Schema(implementation = SubcategoriaDTO.class))
        ))
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subcategoria criada com sucesso", content = @Content()),
        @ApiResponse(responseCode = "400", description = "Bad request, tem alguma coisa errada na requisição", content = @Content())
    })
    @PostMapping()
    public ResponseEntity<Subcategoria> postSubcategoria(@RequestBody SubcategoriaDTO dto){
        return ResponseEntity.ok(subcategoriaService.postSubcategoria(dto));
    }
    @Operation(summary = "Deleta uma Subcategoria pelo id", description = "<h3>Deleta a subcategoria correspondente ao id passado via caminho da requisição</h3>")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Subcategoria deletada com sucesso", content = @Content()),
        @ApiResponse(responseCode = "404", description = "Nenhuma Subcategoria corresponde ao id passado", content = @Content())
    })
    @DeleteMapping("/{id}")
    public void deleteSubcategoriaById(@Parameter(required = true, description = "id da subcategoria")  @PathVariable int id){
        subcategoriaService.deleteByid(id);
    }

}
