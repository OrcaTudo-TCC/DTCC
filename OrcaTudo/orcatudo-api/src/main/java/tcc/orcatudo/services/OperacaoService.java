package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.OperacaoEnum;
import tcc.orcatudo.entitites.StatusEnum;

public interface OperacaoService {

    List<Operacao> getOperacaoByUsuarioId(int id);

    Operacao postOperacaoById(int id, OperacaoEnum operacao, StatusEnum status);

    Operacao putOperacaoStatusById(int id, StatusEnum status);



}
