package com.easyjob.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1,
          max = 10,
          message = "O tamanho do username deve estar entre 1 e 10")
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 1,
          max = 25,
          message = "O tamanho do email deve estar entre 1 e 25")
    private String email;

    @ManyToMany( fetch = FetchType.EAGER)
    private List<Permission> permissoes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Permission> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permission> permissoes) {
        this.permissoes = permissoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
