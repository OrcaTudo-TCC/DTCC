package tcc.orcatudo.services;

import org.springframework.http.ResponseEntity;

import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.OperacaoEnum;

public interface OperacaoService {

    Operacao getOperacaoByUsuarioId(int id);

    ResponseEntity<Operacao> postOperacaoById(int id, OperacaoEnum operacao);



}
