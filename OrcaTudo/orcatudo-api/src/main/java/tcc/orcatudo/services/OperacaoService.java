package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.dtos.OperacaoDTO;
import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.StatusEnum;

public interface OperacaoService {

    List<Operacao> getOperacaoByUsuarioId(int id);

    Operacao postOperacaoById(OperacaoDTO dto);

    Operacao putOperacaoStatusById(int id, StatusEnum status);



}
