package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    @GetMapping()
    public List<Fornecedor> getFornecedors(@RequestParam( required = false) String email, @RequestParam( required = false) Integer id){
        
    }

}
