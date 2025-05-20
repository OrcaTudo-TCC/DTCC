package tcc.orcatudo.bootstrap;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import tcc.orcatudo.dtos.RegisterFornecedorDto;
import tcc.orcatudo.dtos.RegisterUsuarioDto;
import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.entitites.Categoria;
import tcc.orcatudo.entitites.Pedido;
import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.entitites.SubcategoriaFinal;
import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.repository.CarrinhoRepository;
import tcc.orcatudo.repository.CategoriaRepository;
import tcc.orcatudo.repository.PedidoRepository;
import tcc.orcatudo.repository.SubcategoriaFinalRepository;
import tcc.orcatudo.repository.SubcategoriaRepository;
import tcc.orcatudo.services.AuthenticationService;


@Component
public class TestDataInicializer implements ApplicationListener<ContextRefreshedEvent> {
    public final CategoriaRepository categoriaRepository;
    public final SubcategoriaRepository subcategoriaRepository;
    public final SubcategoriaFinalRepository subcategoriaFinalRepository;
    public final AuthenticationService authenticationService;
    public final CarrinhoRepository carrinhoRepository;
    public final PedidoRepository pedidoRepository;
    


    public TestDataInicializer(CategoriaRepository categoriaRepository, SubcategoriaRepository subcategoriaRepository, SubcategoriaFinalRepository subcategoriaFinalRepository, AuthenticationService authenticationService, CarrinhoRepository carrinhoRepository, PedidoRepository pedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.subcategoriaRepository = subcategoriaRepository;
        this.subcategoriaFinalRepository = subcategoriaFinalRepository;
        this.authenticationService = authenticationService;
        this.carrinhoRepository = carrinhoRepository;
        this.pedidoRepository = pedidoRepository;
        
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createTest();
    }

    private void createTest() {
        this.createFornecedor();
        this.createUsuario();
        this.createCategorias();
    }


    private void createCategorias(){
        //cria uma cateogira de teste
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria teste");
        categoria = categoriaRepository.save(categoria);


        //cria uma subcategoria de teste
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setCategoria(categoria);
        subcategoria.setNome("Subcategoria teste");
        subcategoria = subcategoriaRepository.save(subcategoria);

        //cria uma subcategoria final de teste
        SubcategoriaFinal subcategoriaFinal = new SubcategoriaFinal();
        subcategoriaFinal.setSubcategoria(subcategoria);
        subcategoriaFinal.setNome("Subcategoria final teste");
        subcategoriaFinalRepository.save(subcategoriaFinal);
    }

    // private void createSubcategoriaFinal() {
    //     SubcategoriaFinal subcategoriaFinal = new SubcategoriaFinal();
    //     subcategoriaFinal.setSubcategoria(subcategoriaRepository.findById(1).get());
    //     subcategoriaFinal.setNome("Subcategoria final teste");
    //     subcategoriaFinalRepository.save(subcategoriaFinal);

    // }

    // private void createSubcategoria() {
    //     Subcategoria subcategoria = new Subcategoria();
    //     subcategoria.setCategoria(categoriaRepository.findById(1).get());
    //     subcategoria.setNome("Subcategoria teste");
    //     subcategoriaRepository.save(subcategoria);
    // }


    // private void createCategoria() {
    //     Categoria categoria = new Categoria();
    //     categoria.setNome("Categoria teste");
    //     categoriaRepository.save(categoria);
    // }

    private void createUsuario() {
        RegisterUsuarioDto usuario = new RegisterUsuarioDto();
        usuario.setNome("Usuario teste");
        usuario.setDocumento("000.000.000-00");
        usuario.setEmail("teste@gmail.com");
        usuario.setEndereco("Avenida do teste, 12345");
        usuario.setTelefone(12345678);
        usuario.setPassword("admin");
        Usuario usuarioSaved = authenticationService.signupUsuario(usuario);

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(usuarioSaved);
        carrinho.setStatus(true);
        carrinho = carrinhoRepository.save(carrinho);

        Pedido pedido = new Pedido();
        pedido.setCarrinho(carrinho);
        pedido.setData(LocalDateTime.now());
        pedidoRepository.save(pedido);
        
    }

    private void createFornecedor() {
        RegisterFornecedorDto fornecedor = new RegisterFornecedorDto();
        fornecedor.setAreaDeAtuacao("área de atuação teste");
        fornecedor.setAvaliacao(5);
        fornecedor.setDescricao("Descrição teste do fornecedor");
        fornecedor.setDocumento("000.000.000-42");
        fornecedor.setEmail("teste@gmail.com");
        fornecedor.setEndereco("Rua teste,123");
        fornecedor.setNome("fornecedor teste");
        fornecedor.setPassword("admin");
        fornecedor.setRazao_social("razão social teste");
        fornecedor.setTelefone("12345678");
        authenticationService.signupFornecedor(fornecedor);
    }



}
