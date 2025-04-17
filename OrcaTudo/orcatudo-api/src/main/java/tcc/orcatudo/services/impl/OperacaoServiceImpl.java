package tcc.orcatudo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.repository.OperacaoRepository;
import tcc.orcatudo.services.OperacaoService;

@Service
public class OperacaoServiceImpl implements OperacaoService{

    @Autowired
    OperacaoRepository operacaoRepository;



    @Override
    public Operacao getOperacaoByUsuarioId(int id) {
        return operacaoRepository.findByUsuarioId(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum Usuário encontrado com o id: " + id));
    }

    
}
