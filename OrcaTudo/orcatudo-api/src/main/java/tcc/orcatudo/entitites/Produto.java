package tcc.orcatudo.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tcc.orcatudo.dtos.PutProdutoDTO;
import tcc.orcatudo.dtos.SaveProdutoDTO;

@Entity
@Table( name = "produto")
public class Produto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false)
    private String nome;

    @Column( nullable =  false)
    private String descricao;

    @Column( nullable =  false)
    private Double preco;

    @Lob
    private byte[] imagem;

    @ManyToOne
    @JoinColumn( name = "id_subcategoriafinal")
    private SubcategoriaFinal subcategoriaFinal;

    @ManyToOne
    @JoinColumn( name = "id_fornecedor" )
    private Fornecedor fornecedor;


    public Produto() {
    }

    public static Produto fromDTO(PutProdutoDTO dto){
        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setId(dto.getId());
        produto.setImagem(dto.getImagem());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        return produto;
    }
    public static Produto fromDTO(SaveProdutoDTO dto){
        Produto produto = new Produto();
        produto.setDescricao(dto.getDescricao());
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());

        return produto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SubcategoriaFinal getSubcategoriaFinal() {
        return subcategoriaFinal;
    }

    public void setSubcategoriaFinal(SubcategoriaFinal subcategoriaFinal) {
        this.subcategoriaFinal = subcategoriaFinal;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }


    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    

    
}
