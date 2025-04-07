package tcc.orcatudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.orcatudo.entitites.Fornecedor;
import tcc.orcatudo.entitites.Usuario;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

}
