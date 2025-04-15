package tcc.orcatudo.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import tcc.orcatudo.dtos.PostItemCarrinhoDTO;
import tcc.orcatudo.dtos.PutItemCarrinhoDTO;

@Entity
@Table( name = "item_carrinho")
public class ItemCarrinho {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn( name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn( name =  "id_carrinho")
    private Carrinho carrinho;

    public ItemCarrinho() {
    }

    public ItemCarrinho(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Double getSubtotal(){
        if (quantidade != null) {
            return produto.getPreco()  * quantidade;
        }
        return 0.0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public static ItemCarrinho fromDTO(PutItemCarrinhoDTO dto) {
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setId(dto.getId());
        itemCarrinho.setQuantidade(dto.getQuantidade());
        return itemCarrinho;
    }

    public static ItemCarrinho fromDTO(PostItemCarrinhoDTO dto) {
        ItemCarrinho itemCarrinho = new ItemCarrinho();
        itemCarrinho.setQuantidade(dto.getQuantidade());
        return itemCarrinho;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }


    

}
