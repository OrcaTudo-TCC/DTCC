package tcc.orcatudo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Fornecedor;
import tcc.orcatudo.services.FornecedorService;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    @GetMapping()
    public List<Fornecedor> getFornecedor(@RequestParam( required = false) String email, @RequestParam( required = false) Integer id){
        if (id != null) {
            return List.of(fornecedorService.getFornecedorById(id));
        }else if (email != null) {
            return List.of(fornecedorService.getFornecedorByEmail(email));
        }else{
            return fornecedorService.getAllFornecedor();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> putFornecedor(@PathVariable int id , @RequestBody Map<String , String> campos){
        return  ResponseEntity.ok(fornecedorService.updateFornecedor(campos, id));
    }

    @DeleteMapping("/{id}")
    public void deleteFornecedor(@PathVariable int id){
        fornecedorService.deleteFornecedorByID(id);
    }

}
