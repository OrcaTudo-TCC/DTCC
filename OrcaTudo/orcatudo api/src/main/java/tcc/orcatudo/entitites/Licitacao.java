package tcc.orcatudo.entitites;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Licitacao {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)

    @Column( name = "data_inicio")
    private LocalDateTime startdate;
    @Column( name = "data_fim")
    private LocalDateTime endDate;
    @Column(name = "valor_final")
    private double valorFinal;

    private boolean status;

    @OneToOne
    @JoinColumn( name = "id_usuario")
    private Usuario usuario;

    @OneToOne
    @JoinColumn( name = "id_fornecedor")
    private Fornecedor fornecedor;

    @OneToOne
    @JoinColumn( name = "id_produto")
    private Produto produto;

    public Licitacao() {
    }

    public LocalDateTime getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDateTime startdate) {
        this.startdate = startdate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    

}
