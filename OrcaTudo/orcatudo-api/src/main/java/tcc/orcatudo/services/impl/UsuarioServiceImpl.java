package tcc.orcatudo.services.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import tcc.orcatudo.dtos.UsuarioDTO;
import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.repository.UsuarioRepository;
import tcc.orcatudo.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    PasswordEncoder passwordEncoder;

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

    public Usuario putUsuario(UsuarioDTO usuario){
        Usuario updatedUser = new Usuario();
        updatedUser.setId(usuario.getId());
        updatedUser.setDocumento(usuario.getDocumento());
        updatedUser.setEmail(usuario.getEmail());
        updatedUser.setEndereco(usuario.getEndereco());
        updatedUser.setNome(usuario.getNome());
        updatedUser.setTelefone(usuario.getTelefone());
        updatedUser.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(updatedUser);
    }

    public void deleteUsuarioById(int id){
        usuarioRepository.deleteById(id);
    }

    @Override
    public long countUsuario() {
        return usuarioRepository.count();
    }


    


}
