package tcc.orcatudo.services;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tcc.orcatudo.dtos.LoginUsuarioDto;
import tcc.orcatudo.dtos.RegisterUsuarioDto;
import tcc.orcatudo.handler.BusinessException;
import tcc.orcatudo.model.Usuario;
import tcc.orcatudo.repository.UsuarioRepository;

@Service
public class AuthenticationService {
    private final UsuarioRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UsuarioRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario signup(RegisterUsuarioDto input) {
        Usuario user = new Usuario();
                user.setNome(input.getNome());
                user.setEmail(input.getEmail());
                user.setDocumento(input.getDocumento());
                user.setSenha(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public Usuario authenticate(LoginUsuarioDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow(() -> new BusinessException("Email não possui correspondÊncia no banco de dados"));
    }
}