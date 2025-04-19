package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Categoria;
import tcc.orcatudo.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping()
    public List<Categoria> getCategoria(){
        return categoriaService.getCategoria();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> putCategoriaNome(@PathVariable int id, @RequestBody String novoNome){
        return ResponseEntity.ok(categoriaService.updateCategoriaNome(id , novoNome)) ;
    }
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable int id){
        categoriaService.deleteByid(id);
    }
    
}
