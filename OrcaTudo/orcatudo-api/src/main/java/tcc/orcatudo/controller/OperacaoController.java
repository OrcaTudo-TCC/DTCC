package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.OperacaoEnum;
import tcc.orcatudo.entitites.StatusEnum;
import tcc.orcatudo.services.OperacaoService;

@RestController
@RequestMapping("/operacao")
public class OperacaoController {

    @Autowired
    OperacaoService operacaoService;

    @GetMapping("/{id}")
    public List<Operacao> getOperacaoByUsuario(@PathVariable int id){
        return operacaoService.getOperacaoByUsuarioId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Operacao> putStatusByOperacaoId(@PathVariable int id ,@RequestBody StatusEnum status){
        return ResponseEntity.ok(operacaoService.putOperacaoStatusById(id , status));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Operacao> postOperacaoById(@PathVariable int id , @RequestBody OperacaoEnum operacao, @RequestBody StatusEnum status){
        return ResponseEntity.ok(operacaoService.postOperacaoById(id , operacao , status));
    }

}
