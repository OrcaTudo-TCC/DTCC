package tcc.orcatudo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.orcatudo.entitites.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Integer>{

    List<Operacao> findAllByUsuarioId(int id);

}
