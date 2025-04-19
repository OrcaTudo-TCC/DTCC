package tcc.orcatudo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.orcatudo.entitites.ItemCarrinho;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Integer>{

    List<ItemCarrinho> findAllByCarrinhoId(Integer id);

}
