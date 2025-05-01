package tcc.orcatudo.services.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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



    @Override
    public Usuario putUsuario(HashMap<String, String> campos, int id) {
        Usuario toUpdate = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND , "Nenhum usuario com id: "+ id));

        campos.forEach((campo , valor)->{
            switch (campo) {
                case "nome":
                    toUpdate.setNome(valor);
                    break;
                case "email":
                    toUpdate.setEmail(valor);
                    break;
                case "documento":
                    toUpdate.setDocumento(valor);
                    break;
                case "senha":
                    toUpdate.setSenha(valor);
                    break;
                case "telefone":
                    toUpdate.setTelefone(valor);
                    break;
                case "endereco":
                    toUpdate.setEndereco(valor);
                    break;
                default:
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum campo de usuario com nome: "+ campo);
            }
        });

        return usuarioRepository.save(toUpdate);
    }

    public void deleteUsuarioById(int id){
        Usuario toDelete = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        toDelete.setRole(null);
        usuarioRepository.delete(toDelete);
    }

    @Override
    public long countUsuario() {
        return usuarioRepository.count();
    }


    


}
