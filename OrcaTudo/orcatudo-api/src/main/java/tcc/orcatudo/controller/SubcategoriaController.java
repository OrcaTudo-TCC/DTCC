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

import tcc.orcatudo.dtos.SubcategoriaDTO;
import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.services.SubcategoriaService;

@RestController
@RequestMapping("/subcategoria")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    @GetMapping("/{categoria}")
    public List<Subcategoria> getSubcategoriaByCategoria(@PathVariable(required = true) String categoria){
        return subcategoriaService.getByCategoria(categoria);
    }
    @PutMapping("/{id}/nome")
    public ResponseEntity<Subcategoria> putSubcategoriaNome(@PathVariable int id, @RequestBody String novoNome){
        return ResponseEntity.ok(subcategoriaService.updateNome(id , novoNome));
    }
    @PutMapping("/{id}/categoria")
    public ResponseEntity<Subcategoria> putSubcategoriaCategoria(@PathVariable(required = true) int id, @RequestBody int idDaCategoria){
        return ResponseEntity.ok(subcategoriaService.updateCategoria(id , idDaCategoria));
    }
    @PostMapping()
    public ResponseEntity<Subcategoria> postSubcategoria(@RequestBody SubcategoriaDTO dto){
        return ResponseEntity.ok(subcategoriaService.postSubcategoria(dto));
    }
    @DeleteMapping("/{id}")
    public void deleteSubcategoriaById(@PathVariable int id){
        subcategoriaService.deleteByid(id);
    }

}
