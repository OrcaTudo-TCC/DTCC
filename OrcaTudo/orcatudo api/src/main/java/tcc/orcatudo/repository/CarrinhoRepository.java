package tcc.orcatudo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho,Integer> {

}
