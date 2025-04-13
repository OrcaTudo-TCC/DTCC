package tcc.orcatudo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.services.impl.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;


    @GetMapping()
    public List<Usuario> getUsuarioById(@RequestParam(required = false) Integer id , @RequestParam(required = false) String email){
        if (id != null) {
            return  usuarioService.getUsuarioByID(id);
        }else if (email != null) {
            return usuarioService.getUsuarioByEmail(email);
        }

        return usuarioService.getAllUsuario();
    }
    @GetMapping("Count")
    public long countUsuario(){
        return usuarioService.countUsuario();
    }

    @PutMapping()
    @PreAuthorize("hasRole('USUARIO')")
    public ResponseEntity<Usuario> putUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.putUsuario(usuario));
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('USUARIO')")
    public void deleteUsuario(@RequestBody Usuario usuario){
        usuarioService.deleteUsuario(usuario);
    }


}
