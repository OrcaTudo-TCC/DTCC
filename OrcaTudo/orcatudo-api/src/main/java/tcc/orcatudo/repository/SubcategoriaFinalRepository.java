package tcc.orcatudo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.SubcategoriaFinal;

@Repository
public interface SubcategoriaFinalRepository extends JpaRepository<SubcategoriaFinal, Integer> {

    Optional<SubcategoriaFinal> findByNome(String nome);

    List<SubcategoriaFinal> findAllBySubcategoriaNome(String nomeSubcategoria);

}
