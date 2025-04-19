package tcc.orcatudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer>{

    Optional<Categoria> findByNome(String nome);

}
