package tcc.orcatudo.bootstrap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import tcc.orcatudo.dtos.RegisterFornecedorDto;
import tcc.orcatudo.dtos.RegisterUsuarioDto;
import tcc.orcatudo.entitites.Carrinho;
import tcc.orcatudo.entitites.Categoria;
import tcc.orcatudo.entitites.ItemCarrinho;
import tcc.orcatudo.entitites.Operacao;
import tcc.orcatudo.entitites.OperacaoEnum;
import tcc.orcatudo.entitites.Pedido;
import tcc.orcatudo.entitites.Produto;
import tcc.orcatudo.entitites.StatusEnum;
import tcc.orcatudo.entitites.Subcategoria;
import tcc.orcatudo.entitites.SubcategoriaFinal;
import tcc.orcatudo.entitites.Usuario;
import tcc.orcatudo.handler.BusinessException;
import tcc.orcatudo.repository.CarrinhoRepository;
import tcc.orcatudo.repository.CategoriaRepository;
import tcc.orcatudo.repository.FornecedorRepository;
import tcc.orcatudo.repository.ItemCarrinhoRepository;
import tcc.orcatudo.repository.OperacaoRepository;
import tcc.orcatudo.repository.PedidoRepository;
import tcc.orcatudo.repository.ProdutoRepository;
import tcc.orcatudo.repository.SubcategoriaFinalRepository;
import tcc.orcatudo.repository.SubcategoriaRepository;
import tcc.orcatudo.repository.UsuarioRepository;
import tcc.orcatudo.services.AuthenticationService;


@Component
public class TestDataInicializer implements ApplicationListener<ContextRefreshedEvent> {
    public final CategoriaRepository categoriaRepository;
    public final SubcategoriaRepository subcategoriaRepository;
    public final SubcategoriaFinalRepository subcategoriaFinalRepository;
    public final AuthenticationService authenticationService;
    public final CarrinhoRepository carrinhoRepository;
    public final PedidoRepository pedidoRepository;
    public final ProdutoRepository produtoRepository;
    public final FornecedorRepository fornecedorRepository;
    public final UsuarioRepository usuarioRepository;
    public final OperacaoRepository operacaoRepository;
    public final ItemCarrinhoRepository itemCarrinhoRepository;


    public TestDataInicializer(CategoriaRepository categoriaRepository, SubcategoriaRepository subcategoriaRepository, SubcategoriaFinalRepository subcategoriaFinalRepository, AuthenticationService authenticationService, CarrinhoRepository carrinhoRepository, PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, FornecedorRepository fornecedorRepository, UsuarioRepository usuarioRepository, OperacaoRepository operacaoRepository, ItemCarrinhoRepository itemCarrinhoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.subcategoriaRepository = subcategoriaRepository;
        this.subcategoriaFinalRepository = subcategoriaFinalRepository;
        this.authenticationService = authenticationService;
        this.carrinhoRepository = carrinhoRepository;
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.usuarioRepository = usuarioRepository;
        this.operacaoRepository = operacaoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            if (!usuarioRepository.findById(1).isPresent()) {
                this.createTest();
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void createTest() throws IOException {
        this.createFornecedor();
        this.createUsuario();
        this.createCategorias();
        this.createProduto();
        this.createItemCarrinho();
        this.createOperacao();
    }


    private void createCategorias(){
        //cria uma cateogira de teste
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria teste");
        categoria = categoriaRepository.save(categoria);

        Categoria categoria2 = new Categoria();
        categoria2.setNome("Categoria 2");
        categoria2 = categoriaRepository.save(categoria2);


        //cria uma subcategoria de teste
        Subcategoria subcategoria = new Subcategoria();
        subcategoria.setCategoria(categoria);
        subcategoria.setNome("Subcategoria teste");
        subcategoria = subcategoriaRepository.save(subcategoria);

        Subcategoria subcategoria2 = new Subcategoria();
        subcategoria2.setCategoria(categoria);
        subcategoria2.setNome("Subcategoria teste2");
        subcategoria2 = subcategoriaRepository.save(subcategoria2);

        Subcategoria subcategoria3 = new Subcategoria();
        subcategoria3.setCategoria(categoria2);
        subcategoria3.setNome("Subcategoria 3");
        subcategoria3 = subcategoriaRepository.save(subcategoria3);



        //cria uma subcategoria final de teste
        SubcategoriaFinal subcategoriaFinal = new SubcategoriaFinal();
        subcategoriaFinal.setSubcategoria(subcategoria);
        subcategoriaFinal.setNome("Subcategoria final teste");
        subcategoriaFinalRepository.save(subcategoriaFinal);

        SubcategoriaFinal subcategoriaFinal2 = new SubcategoriaFinal();
        subcategoriaFinal2.setSubcategoria(subcategoria);
        subcategoriaFinal2.setNome("final teste 2");
        subcategoriaFinalRepository.save(subcategoriaFinal2);

        SubcategoriaFinal subcategoriaFinal3 = new SubcategoriaFinal();
        subcategoriaFinal3.setSubcategoria(subcategoria2);
        subcategoriaFinal3.setNome("final teste 3");
        subcategoriaFinalRepository.save(subcategoriaFinal3);
    }

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

    private void createProduto() throws IOException{
        Produto produto = new Produto();
        produto.setNome("Produto teste");
        produto.setDescricao("descrição ddo produto teste");
        produto.setFornecedor(fornecedorRepository.findById(1)
        .orElseThrow(() -> new BusinessException("fornecedor teste não encontrado.")));
        //criando byte[] para armazenar a imagme no banco de dados

        Path caminho = Paths.get("imagens\\Logo1.png");
        byte[] imagem = Files.readAllBytes(caminho);
        Byte[] imagemWrapper = new Byte[imagem.length];

        for(int i = 0; i <imagem.length; i++){
            imagemWrapper[i] = imagem[i];
        }

        produto.setImagem(imagem);

        produto.setPreco(99.9);
        produto.setSubcategoriaFinal(subcategoriaFinalRepository.findById(1).
        orElseThrow(() -> new BusinessException("subcateogoria final teste não encontrado.")));
        produtoRepository.save(produto);
    }

    private void createItemCarrinho(){
        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produtoRepository.findById(1)
        .orElseThrow(() -> new BusinessException("produto teste não encontrado.")));
        item.setQuantidade(5);
        item.setCarrinho(carrinhoRepository.findById(1)
        .orElseThrow(() -> new BusinessException("carrinho teste não encontrado.")));
        itemCarrinhoRepository.save(item);
    }

    private void createOperacao(){
        Operacao operacao = new Operacao();
        operacao.setData(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        operacao.setOperacao(OperacaoEnum.COTACAO);
        operacao.setStatus(StatusEnum.PENDENTE);
        operacao.setUsuario(usuarioRepository.findById(1)
        .orElseThrow(() -> new BusinessException("carrinho teste não encontrado.")));
        operacaoRepository.save(operacao);
        
    }
}
