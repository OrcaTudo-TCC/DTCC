package tcc.orcatudo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.repository.CarrinhoRepository;
import tcc.orcatudo.services.CarrinhoService;

@Service
public class CarrinhoServiceImpl implements CarrinhoService{

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Override
    public Carrinho getCarrinhoByUsuarioId(int id) {
        return carrinhoRepository.findByUsuarioId(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum usuario encontrado com o id: " + id));
    }

    

}
