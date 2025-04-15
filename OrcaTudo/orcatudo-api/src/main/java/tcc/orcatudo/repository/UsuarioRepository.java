package tcc.orcatudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario , Integer>{

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
