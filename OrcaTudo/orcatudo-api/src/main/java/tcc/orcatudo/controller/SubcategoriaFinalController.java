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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import tcc.orcatudo.dtos.SubcategoriaFinalDTO;
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

    
    @PutMapping("/{idSubcategoriaFinal}/nome")
    public ResponseEntity<SubcategoriaFinal> putSubcategoriaFinal(@PathVariable int idSubcategoriaFinal, @RequestBody String novoNome){
        return ResponseEntity.ok(subFinalService.updateNomeSubcategoriaFinal(idSubcategoriaFinal , novoNome));
    }
    @PutMapping("/{idSubcategoriaFinal}/update_subcategoria")
    public ResponseEntity<SubcategoriaFinal> putSubcategoriaFinal(@PathVariable int idSubcategoriaFinal, @RequestBody int idSubcategoria){
        return ResponseEntity.ok(subFinalService.updateSubcategoria(idSubcategoriaFinal , idSubcategoria));
    }
    @PostMapping()
    public ResponseEntity<SubcategoriaFinal> postSubcategoriaFinal(@RequestBody SubcategoriaFinalDTO dto){
        return ResponseEntity.ok(subFinalService.postSubcategoriaFinal(dto));
    }
    @DeleteMapping("/{id}")
    public void deleteSubcategoriaFinal(@PathVariable int id){
        subFinalService.deleteByid(id);
    }
}
