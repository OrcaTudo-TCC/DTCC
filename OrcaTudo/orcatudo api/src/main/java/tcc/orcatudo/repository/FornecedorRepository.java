package tcc.orcatudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.orcatudo.entitites.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor , Integer>{

        Optional<Fornecedor> findByEmail(String email);

    boolean existsByEmail(String email);

}
