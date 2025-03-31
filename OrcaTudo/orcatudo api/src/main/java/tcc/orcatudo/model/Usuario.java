package tcc.orcatudo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(length = 100 , nullable = false)
    private String email;

    @Column(name = "cpf_cnpj", length = 14 , nullable = false)
    private String documento;
    @Column(length = 11 , nullable = false)
    private String senha;

    @Column(length = 11 , nullable = true)
    private Integer telefone;

    @Column(length = 250 , nullable = true)
    private String endereco;
    

    public Usuario() {}


    public boolean validadeRequiredFields(){
        if (getDocumento() == null || getEmail() == null || getSenha() == null) {
            return false;
        }
        return true;
    }
    public Integer getId() {
        return id;
    }

    


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getDocumento() {
        return documento;
    }


    public void setDocumento(String documento) {
        this.documento = documento;
    }


    public int getTelefone() {
        return telefone;
    }


    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }


    public String getEndereco() {
        return endereco;
    }


    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getSenha() {
        return senha;
    }


    public void setSenha(String senha) {
        this.senha = senha;
    }


    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }
    

    
}
