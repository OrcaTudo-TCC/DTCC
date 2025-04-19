package tcc.orcatudo.services.impl;

import java.util.List;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.dtos.PostCarrinhoDTO;
import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.handler.BusinessException;
import tcc.orcatudo.repository.CarrinhoRepository;
import tcc.orcatudo.repository.UsuarioRepository;
import tcc.orcatudo.services.CarrinhoService;

@Service
public class CarrinhoServiceImpl implements CarrinhoService{

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Carrinho getCarrinhoByUsuarioId(int id) {
        List<Carrinho> carrinhos = carrinhoRepository.findAllByUsuarioId(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum usuario encontrado com o id: " + id));
        return carrinhos.stream().filter(c -> c.isStatus()).reduce((a , b) -> {  throw new BusinessException("Mais de um carrinho ativo ao mesmo tempo");})
        .get();
        
    }

    @Override
    public Carrinho postCarrinho(PostCarrinhoDTO carrinhoDTO) {
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuarioRepository.findByEmail(carrinhoDTO.getEmailDoUsuario())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum usuário encontrado com email: "+ carrinhoDTO.getEmailDoUsuario())));
        carrinho.setStatus(true);
        return carrinhoRepository.save(carrinho);
    }

    @Override
    public Carrinho changeCarrinhoStatus(int id, boolean status) {
        Carrinho updatedCarrinho = carrinhoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum carrinho encontrado com id: " + id));
        updatedCarrinho.setStatus(status);
        return carrinhoRepository.save(updatedCarrinho);
    }
    
    

}
