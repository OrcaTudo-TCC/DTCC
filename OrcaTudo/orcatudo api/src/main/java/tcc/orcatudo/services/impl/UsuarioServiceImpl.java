package tcc.orcatudo.services.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.handler.MissingFieldsException;
import tcc.orcatudo.repository.UsuarioRepository;
import tcc.orcatudo.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarioByEmail(String email){
        return List.of(usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado")));
    }

    public List<Usuario> getUsuarioByID(Integer id){
        return usuarioRepository.findById(id).map(List::of).orElseGet(Collections::emptyList);
    }

    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }

    public Usuario putUsuario(Usuario usuario){
        if (!usuario.validadeRequiredFields() || usuario.getId() == null ) {
            throw new MissingFieldsException();
        }
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Usuario usuario){
        if (!usuario.validadeRequiredFields() || usuario.getId() == null) {
            throw new MissingFieldsException();
        }
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            usuarioRepository.delete(usuario);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public long countUsuario() {
        return usuarioRepository.count();
    }

    


}
