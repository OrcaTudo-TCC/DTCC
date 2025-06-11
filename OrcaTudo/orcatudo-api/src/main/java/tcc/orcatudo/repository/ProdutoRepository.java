package tcc.orcatudo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

    Optional<Produto> findByNome(String nome);

    List<Produto> findBySubcategoriaFinalNome(String nome);

    List<Produto> findByFornecedorNome(String nome);

    long countBySubcategoriaFinalNome(String nome);

    long countByFornecedorNome(String nome);

    List<Produto> findByNomeContaining(String nome);

}
