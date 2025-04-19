package tcc.orcatudo.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.OperacaoEnum;
import tcc.orcatudo.entitites.StatusEnum;
import tcc.orcatudo.repository.OperacaoRepository;
import tcc.orcatudo.repository.UsuarioRepository;
import tcc.orcatudo.services.OperacaoService;

@Service
public class OperacaoServiceImpl implements OperacaoService{

    @Autowired
    OperacaoRepository operacaoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public List<Operacao> getOperacaoByUsuarioId(int id) {
        return operacaoRepository.findAllByUsuarioId(id);
    }

    @Override
    public Operacao putOperacaoStatusById(int id, StatusEnum status) {
        Operacao opercaoToUpdate = operacaoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma operacao encontrada com o id: "+ id));
        opercaoToUpdate.setStatus(status);
        return operacaoRepository.save(opercaoToUpdate);
    }

    @Override
    public Operacao postOperacaoById(int idUsuario ,OperacaoEnum operacao, StatusEnum status) {
        Operacao operacaoToSave = new Operacao();
        operacaoToSave.setOperacao(operacao);
        operacaoToSave.setUsuario(usuarioRepository.findById(idUsuario)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum usuário encontrado com id: "+ idUsuario)));
        operacaoToSave.setData(LocalDateTime.now());
        operacaoToSave.setStatus(status);
        return operacaoRepository.save(operacaoToSave);
    }

    
}
