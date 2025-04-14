package tcc.orcatudo.services;



import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tcc.orcatudo.dtos.LoginUsuarioDto;
import tcc.orcatudo.dtos.RegisterFornecedorDto;
import tcc.orcatudo.dtos.RegisterUsuarioDto;
import tcc.orcatudo.entitites.Fornecedor;
import tcc.orcatudo.entitites.Role;
import tcc.orcatudo.entitites.RoleEnum;
import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.handler.BusinessException;
import tcc.orcatudo.repository.FornecedorRepository;
import tcc.orcatudo.repository.RoleRepository;
import tcc.orcatudo.repository.UsuarioRepository;

@Service
public class AuthenticationService {
    private final UsuarioRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;

    private final RoleRepository roleRepository;

    private final FornecedorRepository fornecedorRepository;

    public AuthenticationService(
        UsuarioRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        RoleRepository roleRepository,
        FornecedorRepository fornecedorRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public Usuario signupUsuario(RegisterUsuarioDto input) {

        Optional<Role> roleOptional = roleRepository.findByName(RoleEnum.USUARIO);
        if (roleOptional.isEmpty()) {
            return null;
        }
        
            Usuario user = new Usuario();
                    user.setNome(input.getNome());
                    user.setEmail(input.getEmail());
                    user.setDocumento(input.getDocumento());
                    user.setRole(roleOptional.get());
                    user.setTelefone(input.getTelefone());
                    user.setEndereco(input.getEndereco());
                    user.setSenha(passwordEncoder.encode(input.getPassword()));
    
            return userRepository.save(user);
    }
    public Fornecedor signupFornecedor(RegisterFornecedorDto fornecedorDto) {

        Optional<Role> roleOptional = roleRepository.findByName(RoleEnum.FORNECEDOR);

        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDto.getNome());
        fornecedor.setEmail(fornecedorDto.getEmail());
        fornecedor.setDocumento(fornecedorDto.getDocumento());
        fornecedor.setRole(roleOptional.get());
        fornecedor.setSenha(passwordEncoder.encode(fornecedorDto.getPassword()));
        fornecedor.setRazao_social(fornecedorDto.getRazao_social());
        fornecedor.setArea_de_atuacao(fornecedorDto.getAreaDeAtuacao());
        fornecedor.setEndereco(fornecedorDto.getEndereco());
        fornecedor.setAvaliacao(fornecedorDto.getAvaliacao());
        fornecedor.setDescricao(fornecedorDto.getDescricao());
        fornecedor.setTelefone(fornecedorDto.getTelefone());

        return fornecedorRepository.save(fornecedor);
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

    public Fornecedor authenticateFornecedor(LoginUsuarioDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return fornecedorRepository.findByEmail(input.getEmail()).orElseThrow(() -> new BusinessException("Email não possui correspondência no banco de dados"));
    }

}