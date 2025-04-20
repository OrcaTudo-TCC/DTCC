package tcc.orcatudo.services;

import java.util.List;
import java.util.Map;

import tcc.orcatudo.entitites.Usuario;

public interface UsuarioService {

    List<Usuario> getUsuarioByEmail(String email);

    List<Usuario> getUsuarioByID(Integer id);

    List<Usuario> getAllUsuario();

    Usuario putUsuario(Map<String,String> campos , int id);

    void deleteUsuarioById(int id);

    long countUsuario();

}
