package tcc.orcatudo.entitites;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( nullable = false)
    private String nome;

    @Column( nullable =  false)
    private String descricao;

    @Column( nullable =  false)
    private BigDecimal preco;

    @Lob
    private Byte[] imagem;

    @ManyToOne
    private SubcategoriaFinal SubcategoriaFinal;
}
