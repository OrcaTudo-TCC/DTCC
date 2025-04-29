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

import tcc.orcatudo.entitites.Categoria;
import tcc.orcatudo.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
@Tag( name = "Categorias", description = "Todas as operações relacionada com as categorias")

public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Operation(summary = "Retorna uma Lista com todas as categorias", description = "retorna uma lista com todas as categorias existentes")
    @ApiResponse(
        responseCode = "200",
        description = "Buscou todas as categorias com sucesso",
        content = @Content(
            schema = @Schema(implementation = Categoria.class)
        ))
    @GetMapping()
    public List<Categoria> getCategoria(){
        return categoriaService.getCategoria();
    }

    @Operation(
        summary = "Atualiza o nome categoria por id",
        description = "<h3>Altera o nome da categoria correspondente ao id passado, no corpo da requsição devera ter o novo nome<br>Não devera ser colocado aspas no corpo da requisição</h3> ",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "<h3>devera conter o novo nome da categoria sem aspas</h3>",
            content = @Content(
                schema = @Schema(
                    type = "object",
                    example = "novo nome"
                )
            )
        ))
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Editado com sucesso",
            content = @Content(
                schema = @Schema(implementation = Categoria.class)
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Nenhuma categoria encontrada com o id fornecido",
            content = @Content()
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> putCategoriaNome(@Parameter(required = true, description = "Id da Categoria")@PathVariable( required = true) int id, @RequestBody String novoNome){
        return ResponseEntity.ok(categoriaService.updateCategoriaNome(id , novoNome)) ;
    }
    @Operation( summary = "Adicionar uma categoria", description = "<h3>Cria uma Categoria com o nome passado pelo caminho da requisição<br> exemplo: \"\\categoria\\ferramentas\"")
    @ApiResponse(responseCode = "200", description = "Categoria criada com sucesso", content = @Content())
    @PostMapping("/{nome}")
    public ResponseEntity<Categoria> postCategoria(@Parameter(required = true, description = "nome da Categoria",example = "ferramentas")@PathVariable( required = true) String nome){
        return ResponseEntity.ok(categoriaService.postCategoria(nome));
    }

    @Operation(summary = "Deleta a Categoria", description = "<h3>Deleta a Categoria correspondente ao id passado no caminho da requisição</h3>")
    @ApiResponse(responseCode = "200", description = "Deletou com sucesso a categoria",content = @Content())
    @ApiResponse(responseCode = "404", description = "Nenhuma correspondência ao id passado")
    @DeleteMapping("/{id}")
    public void deleteCategoria(@Parameter(required = true, description = "Id da categoria")@PathVariable int id){
        categoriaService.deleteByid(id);
    }
    
}
