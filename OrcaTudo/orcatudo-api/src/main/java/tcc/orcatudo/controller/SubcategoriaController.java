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

import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.services.SubcategoriaService;

@RestController
@RequestMapping("/subcategoria")
public class SubcategoriaController {

    @Autowired
    SubcategoriaService subcategoriaService;

    @GetMapping("/{categoria}")
    public List<Subcategoria> getSubcategoriaByCategoria(@PathVariable String nomeCategoria){
        return subcategoriaService.getByCategoria(nomeCategoria);
    }
    @PutMapping("/{id}/nome")
    public ResponseEntity<Subcategoria> putSubcategoriaNome(@PathVariable int idDaSubcategoria, @RequestBody String novoNome){
        return ResponseEntity.ok(subcategoriaService.updateNome(idDaSubcategoria , novoNome));
    }
    @PutMapping("/{id}/categoria")
    public ResponseEntity<Subcategoria> putSubcategoriaCategoria(@PathVariable int idDaSubcategoria, @RequestBody int idDaCategoria){
        return ResponseEntity.ok(subcategoriaService.updateCategoria(idDaSubcategoria , idDaCategoria));
    }
    @PostMapping()
    public ResponseEntity<Subcategoria> postSubcategoria(@RequestBody String nomeDaCategoria, @RequestBody String nome){
        return ResponseEntity.ok(subcategoriaService.postSubcategoria(nomeDaCategoria, nome));
    }
    @DeleteMapping("/{id}")
    public void deleteSubcategoriaById(@PathVariable int id){
        subcategoriaService.deleteByid(id);
    }

}
