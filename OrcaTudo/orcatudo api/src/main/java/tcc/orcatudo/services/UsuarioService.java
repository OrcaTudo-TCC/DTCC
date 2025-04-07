package tcc.orcatudo.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.handler.BusinessException;
import tcc.orcatudo.handler.MissingFieldsException;
import tcc.orcatudo.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarioByEmail(String email){
        return List.of(usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new BusinessException("Email do usuário não corresponde no Banco de dados")));
    }

    public List<Usuario> getUsuarioByID(Integer id){
        return usuarioRepository.findById(id).map(List::of).orElseGet(Collections::emptyList);
    }

    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }

    public void putUsuario(Usuario usuario){
        if (!usuario.validadeRequiredFields() || usuario.getId() == null ) {
            throw new MissingFieldsException();
        }
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Usuario usuario){
        if (!usuario.validadeRequiredFields() || usuario.getId() == null) {
            throw new MissingFieldsException();
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            usuarioRepository.delete(usuario);
        }else{
            throw new BusinessException("usuário não encontrado");
        }
        
        
    }


}
