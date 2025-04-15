package tcc.orcatudo.services;

import java.util.List;

import tcc.orcatudo.dtos.UsuarioDTO;
import tcc.orcatudo.entitites.Usuario;

public interface UsuarioService {

    List<Usuario> getUsuarioByEmail(String email);

    List<Usuario> getUsuarioByID(Integer id);

    List<Usuario> getAllUsuario();

    Usuario putUsuario(UsuarioDTO usuario);

    void deleteUsuarioById(int id);

    long countUsuario();

}
