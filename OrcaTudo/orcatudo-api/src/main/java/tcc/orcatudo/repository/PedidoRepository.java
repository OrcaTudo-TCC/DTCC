package tcc.orcatudo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.orcatudo.entitites.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

    Optional<Pedido> findByCarrinhoId(int id);

    List<Pedido> findAllByCarrinhoId(int id);

}
