package tcc.orcatudo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.OperacaoEnum;
import tcc.orcatudo.services.OperacaoService;

@RestController
@RequestMapping("/operacao")
public class OperacaoController {

    @Autowired
    OperacaoService operacaoService;

    @GetMapping("/{id}")
    public Operacao getOperacaoByUsuario(@PathVariable int id){
        return operacaoService.getOperacaoByUsuarioId(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Operacao> postOperacaoById(@PathVariable int id , @RequestBody OperacaoEnum operacao){
        return operacaoService.postOperacaoById(id , operacao);
    }

}
