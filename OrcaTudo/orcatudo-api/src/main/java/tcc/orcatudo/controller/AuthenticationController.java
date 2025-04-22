package tcc.orcatudo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
@Tag(name = "Usuarios")
public class AuthenticationController {
    private final JwtService jwtService;
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Registra um usuário", description = "Registra um usuário com os atributos passados via pelo Body da requisição")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200", 
            description = "Usuário criado com sucesso.",
            content = @Content(schema = @Schema(implementation = Usuario.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Bad Request: há algum dado inválido na requisição",
            content = @Content()
        )
    })
    @PostMapping("/signupUsuario")
    public ResponseEntity<Usuario> register(@RequestBody RegisterUsuarioDto registerUserDto) {
        Usuario registeredUser = authenticationService.signupUsuario(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }
    @Operation(
        summary = "Registra um Fornecedor",
        description = "Registra um fornecedor com os atributos passados via pelo Body da requisição")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Forncedor criado com sucesso",
            content = @Content(schema = @Schema(implementation = Fornecedor.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description =  "Bad request: há algum dado inválido na requisição",
            content = @Content()
        )
    })
    @PostMapping("/signupFornecedor")
    public ResponseEntity<Fornecedor> registerFornecedor(@RequestBody RegisterFornecedorDto registerFornecedorDto){
        Fornecedor registeredFornecedor = authenticationService.signupFornecedor(registerFornecedorDto);

        return ResponseEntity.ok(registeredFornecedor);
    }

    @Operation(summary = "Login de Usuário", description = "Faça login no usuario passando password e email do usuário<br> || Retorna um Token de autenticação JWT e o tempo para expirar")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Login feito com sucesso.",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuário não encontrado.",
            content = @Content()
        )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUsuarioDto loginUserDto) {
        Usuario authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
    @Operation(summary = "Login de Fornecedor", description = "Login de forncedor passando password e email do fornecedor<br>|| Retorna um token de autenticação JWT")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Login feito com sucesso",
            content = @Content(schema = @Schema(implementation = LoginResponse.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Fornecedor não encontrado",
            content = @Content()
        )
    })
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