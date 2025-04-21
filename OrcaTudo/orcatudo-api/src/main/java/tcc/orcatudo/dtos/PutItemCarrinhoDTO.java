package tcc.orcatudo.dtos;

public class PutItemCarrinhoDTO {

    private int idDoItem;

    private int quantidade;

    private String nomeDoProduto;

    private int idDoCarrinho;



    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public int getIdDoCarrinho() {
        return idDoCarrinho;
    }

    public void setIdDoCarrinho(int idDoCarrinho) {
        this.idDoCarrinho = idDoCarrinho;
    }

    public int getIdDoItem() {
        return idDoItem;
    }

    public void setIdDoItem(int idDoItem) {
        this.idDoItem = idDoItem;
    }

    
}
