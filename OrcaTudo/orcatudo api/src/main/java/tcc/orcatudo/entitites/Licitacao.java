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

}
