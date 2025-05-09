package tcc.orcatudo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.entitites.Usuario;
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
        List<Carrinho> carrinhos = carrinhoRepository.findAllByUsuarioId(id);

        List<Carrinho> ativos = carrinhos.stream().filter(Carrinho::isStatus).toList();

        if (ativos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse usuário não possui nenhum carrinho ativo");
        }else{
            return ativos.get(0);
        }
        
    }

    @Override
    public Carrinho postCarrinho(String emailUsuario) {
        
        Usuario carrinhoUusuario = usuarioRepository.findByEmail(emailUsuario)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum usuario encontrado com o email: " + emailUsuario));


        List<Carrinho> CarrinhosAtivos = carrinhoRepository.findAllByUsuarioId(carrinhoUusuario.getId())
        .stream().filter(Carrinho::isStatus).toList();

        if (CarrinhosAtivos.isEmpty()) {
            Carrinho carrinho = new Carrinho();
            carrinho.setUsuario(usuarioRepository.findByEmail(emailUsuario)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum usuário encontrado com email: "+ emailUsuario)));
            carrinho.setStatus(true);
            return carrinhoRepository.save(carrinho);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Operação não realizada, mais de um carrinho ativo encontrado para o usuário com email: " + emailUsuario);
        }


    }


    
    

}
