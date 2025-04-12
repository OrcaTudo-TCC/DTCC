package tcc.orcatudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.SubcategoriaFinal;

@Repository
public interface SubcategoriaRepository extends JpaRepository<SubcategoriaFinal, Integer>{

}
