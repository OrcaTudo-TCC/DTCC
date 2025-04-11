package tcc.orcatudo.entitites;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name = "subcategoria")
public class Subcategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    @ManyToOne
    @JoinColumn( name = "id_categoria")
    private Categoria categoria; 

    @OneToMany( mappedBy = "subcategoria" , cascade = CascadeType.ALL , orphanRemoval = true)
    private List<SubcategoriaFinal> subcategoriaFinais;

    public Subcategoria() {
    }

    public Subcategoria(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<SubcategoriaFinal> getSubcategoriaFinais() {
        return subcategoriaFinais;
    }

    public void setSubcategoriaFinais(List<SubcategoriaFinal> subcategoriaFinais) {
        this.subcategoriaFinais = subcategoriaFinais;
    }

    
}
