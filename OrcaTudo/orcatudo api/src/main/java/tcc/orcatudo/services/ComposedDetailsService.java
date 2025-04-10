package tcc.orcatudo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Data;


@Service
@Data
public class ComposedDetailsService implements UserDetailsService{

    @Autowired
    private final FornecedorDetailsService fornecedorDetailsService;

    @Autowired
    private final UsuarioDetailsService usuarioDetailsService;

    private List<UserDetailsService> services;

    @PostConstruct
    public void setServices() {
        List<UserDetailsService> new_services = new ArrayList<>();
        new_services.add(this.fornecedorDetailsService); // injection
        new_services.add(this.usuarioDetailsService);     // injection
        this.services = new_services;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for(UserDetailsService service : services){
            try {
                UserDetails user = service.loadUserByUsername(username);
                return user;
            } catch (Exception e) {
                continue;
            }
        }
        throw new UsernameNotFoundException("Usuario ou fornecedor não encontrado.");
    }

}
