package tcc.orcatudo.dtos;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveProdutoDTO {

    private String nome;

    private String descricao;

    private Double preco;

    private MultipartFile imagemFile;

    private String nomeDasubcategoriaFinal;

    private String nomeDoFornecedor;

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

    public MultipartFile getImagemFile() {
        return imagemFile;
    }

    public void setImagemFile(MultipartFile imagemFile) {
        this.imagemFile = imagemFile;
    }

    

}
