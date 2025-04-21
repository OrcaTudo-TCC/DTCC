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

import tcc.orcatudo.dtos.SubcategoriaFinalDTO;
import tcc.orcatudo.entitites.SubcategoriaFinal;
import tcc.orcatudo.services.SubcategoriaFinalService;

@RestController
@RequestMapping("/subcategoriafinal")
public class SubcategoriaFinalController {

    @Autowired
    SubcategoriaFinalService subFinalService;

    @GetMapping("/{subcategoria}")
    public List<SubcategoriaFinal> getSubcategoriaFinalBySubcategoria(@PathVariable String subcategoria){
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
