package tcc.orcatudo.dtos;

public class PutProdutoDTO {
    
    private Integer id;

    private String nome;

    private String descricao;

    private Double preco;

    private byte[] imagem;

    private String nomeDasubcategoriaFinal;

    private String nomeDoFornecedor;

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

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


    public String getNomeDasubcategoriaFinal() {
        return nomeDasubcategoriaFinal;
    }

    public void setNomeDasubcategoriaFinal(String nomeDasubcategoriaFinal) {
        this.nomeDasubcategoriaFinal = nomeDasubcategoriaFinal;
    }

    public String getNomeDoFornecedor() {
        return nomeDoFornecedor;
    }

    public void setNomeDoFornecedor(String nomeDoFornecedor) {
        this.nomeDoFornecedor = nomeDoFornecedor;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    

}
