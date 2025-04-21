package tcc.orcatudo.dtos;

import tcc.orcatudo.entitites.OperacaoEnum;
import tcc.orcatudo.entitites.StatusEnum;

public class OperacaoDTO {

    int idUsuario;

    OperacaoEnum operacao;

    StatusEnum status;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public OperacaoEnum getOperacao() {
        return operacao;
    }

    public void setOperacao(OperacaoEnum operacao) {
        this.operacao = operacao;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    
}
