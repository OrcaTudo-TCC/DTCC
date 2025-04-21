package tcc.orcatudo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.Subcategoria;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Integer>{

    List<Subcategoria> findAllByCategoriaNome(String nome);

    Optional<Subcategoria> findByNome(String nome);

}
