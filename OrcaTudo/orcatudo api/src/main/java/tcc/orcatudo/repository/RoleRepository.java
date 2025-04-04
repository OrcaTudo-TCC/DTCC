package tcc.orcatudo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tcc.orcatudo.entitites.Role;
import tcc.orcatudo.entitites.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role , Integer> {
    Optional<Role> findByName(RoleEnum name);
}
