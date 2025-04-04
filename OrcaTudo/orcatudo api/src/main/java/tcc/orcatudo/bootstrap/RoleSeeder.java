package tcc.orcatudo.bootstrap;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import tcc.orcatudo.entitites.Role;
import tcc.orcatudo.entitites.RoleEnum;
import tcc.orcatudo.repository.RoleRepository;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent>{
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USUARIO , RoleEnum.FORNECEDOR};
        Map<RoleEnum , String> roleDescriptionMap = Map.of(
            RoleEnum.USUARIO, "role de Usuario/Cliente",
            RoleEnum.FORNECEDOR, "Role de fornecedor"
        );

        Arrays.stream(roleNames).forEach((rolename)->{
            Optional<Role> optionalRole = roleRepository.findByName(rolename);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(rolename);
                roleToCreate.setDescription(roleDescriptionMap.get(rolename));

                roleRepository.save(roleToCreate);
            });
        }

        );
    }

}
