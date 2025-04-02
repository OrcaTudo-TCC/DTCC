package tcc.orcatudo.model;



import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")

public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(length = 100 , nullable = false)
    private String email;

    @Column(name = "cpf_cnpj", length = 14 , nullable = false)
    private String documento;
    @Column(length = 200 , nullable = false)
    private String senha;

    private String nome;


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


    public Integer getTelefone() {
        return telefone;
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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


    @Override
    public String getPassword() {
        return senha;
    }


    @Override
    public String getUsername() {
        return email;
    }
    

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
