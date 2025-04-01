package tcc.orcatudo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.handler.MissingFieldsException;
import tcc.orcatudo.model.Usuario;
import tcc.orcatudo.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;


    @GetMapping()
    public List<Usuario> getUsuarioById(@RequestParam(required = false) Integer id , @RequestParam(required = false) String email){
        if (id != null) {
            return  usuarioRepository.findById(id).map(List::of).orElseGet(Collections::emptyList);
        }else if (email != null) {
            return List.of(usuarioRepository.findByEmail(email));
        }
        List<Usuario> usuarios =usuarioRepository.findAll();
        return usuarios;

    }
    @PostMapping()
    public boolean postUsuario(@RequestBody Usuario usuario){
        if (!usuario.validadeRequiredFields()) {
            throw new MissingFieldsException();
        }
        usuarioRepository.save(usuario);
        return true;
    }

    @PutMapping()
    public void putUsuario(@RequestBody Usuario usuario){
        if (!usuario.validadeRequiredFields() || usuario.getId() == null ) {
            throw new MissingFieldsException();
        }
        usuarioRepository.save(usuario);
    }
    @DeleteMapping()
    public void deleteUsuario(@RequestBody Usuario usuario){
        if (!usuario.validadeRequiredFields() || usuario.getId() == null) {
            throw new MissingFieldsException();
        }
        usuarioRepository.delete(usuario);
    }


}
