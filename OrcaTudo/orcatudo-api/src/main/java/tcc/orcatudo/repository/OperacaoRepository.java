package tcc.orcatudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tcc.orcatudo.entitites.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Integer>{

    Optional<Operacao> findByUsuarioId(int id);

}
