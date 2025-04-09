package tcc.orcatudo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tcc.orcatudo.dtos.LoginUsuarioDto;
import tcc.orcatudo.dtos.RegisterFornecedorDto;
import tcc.orcatudo.dtos.RegisterUsuarioDto;
import tcc.orcatudo.entitites.Fornecedor;
import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.responses.LoginResponse;
import tcc.orcatudo.services.AuthenticationService;
import tcc.orcatudo.services.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signupUsuario")
    public ResponseEntity<Usuario> register(@RequestBody RegisterUsuarioDto registerUserDto) {
        Usuario registeredUser = authenticationService.signupUsuario(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/signupFornecedor")
    public ResponseEntity<Fornecedor> registerFornecedor(@RequestBody RegisterFornecedorDto registerFornecedorDto){
        Fornecedor registeredFornecedor = authenticationService.signupFornecedor(registerFornecedorDto);

        return ResponseEntity.ok(registeredFornecedor);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUsuarioDto loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    @PostMapping("/loginFornecedor")
    public ResponseEntity<LoginResponse> authenticateFornecedor(@RequestBody LoginUsuarioDto loginUsuarioDto){
        Fornecedor authenticatedForn = authenticationService.authenticateFornecedor(loginUsuarioDto);

        String jwtToken = jwtService.generateToken(authenticatedForn);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}