package tcc.orcatudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tcc.orcatudo.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario , Integer>{

    Usuario findByEmail(String email);

    boolean existsByEmail(String email);
}
