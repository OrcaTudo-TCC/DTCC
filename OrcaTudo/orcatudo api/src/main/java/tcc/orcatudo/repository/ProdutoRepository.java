package tcc.orcatudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
