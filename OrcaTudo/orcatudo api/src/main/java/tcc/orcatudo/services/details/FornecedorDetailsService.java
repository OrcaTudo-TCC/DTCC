package tcc.orcatudo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tcc.orcatudo.repository.FornecedorRepository;

@Service
public class FornecedorDetailsService implements UserDetailsService{

    private final FornecedorRepository fornecedorRepository;

    public FornecedorDetailsService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return fornecedorRepository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Fornecedor não encontrado" + username ));
    }

}
