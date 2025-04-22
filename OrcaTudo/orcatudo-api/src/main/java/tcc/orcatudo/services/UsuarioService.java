package tcc.orcatudo.services;

import java.util.HashMap;
import java.util.List;

import tcc.orcatudo.entitites.Usuario;

public interface UsuarioService {

    List<Usuario> getUsuarioByEmail(String email);

    List<Usuario> getUsuarioByID(Integer id);

    List<Usuario> getAllUsuario();

    Usuario putUsuario(HashMap<String,String> campos , int id);

    void deleteUsuarioById(int id);

    long countUsuario();

}
