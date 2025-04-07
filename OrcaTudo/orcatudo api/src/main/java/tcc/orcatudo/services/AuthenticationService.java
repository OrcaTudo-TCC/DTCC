package tcc.orcatudo.services;



import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tcc.orcatudo.dtos.LoginUsuarioDto;
import tcc.orcatudo.dtos.RegisterDto;
import tcc.orcatudo.dtos.RegisterFornecedorDto;
import tcc.orcatudo.dtos.RegisterUsuarioDto;
import tcc.orcatudo.entitites.Role;
import tcc.orcatudo.entitites.RoleEnum;
import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.handler.BusinessException;
import tcc.orcatudo.repository.RoleRepository;
import tcc.orcatudo.repository.UsuarioRepository;

@Service
public class AuthenticationService {
    private final UsuarioRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    public AuthenticationService(
        UsuarioRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        RoleRepository roleRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Usuario signup(RegisterDto input) {

        RoleEnum Enum = null;

        if (input instanceof RegisterUsuarioDto) {
            Enum = RoleEnum.USUARIO;
        }
        if (input instanceof RegisterFornecedorDto) {
            Enum = RoleEnum.FORNECEDOR;
        }
        Optional<Role> roleOptional = roleRepository.findByName(Enum);
        if (roleOptional.isEmpty()) {
            return null;
        }
        



        Usuario user = new Usuario();
                user.setNome(input.getNome());
                user.setEmail(input.getEmail());
                user.setDocumento(input.getDocumento());
                user.setRole(roleOptional.get());
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