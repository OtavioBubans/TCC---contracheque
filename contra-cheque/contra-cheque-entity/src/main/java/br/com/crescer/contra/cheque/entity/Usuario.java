/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author matheus.schmitz
 */
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Lob
    @Column(name = "EMAIL", nullable = false, length = 100)
    private String email;

    @Lob
    @Column(name = "SENHA", nullable = false, length = 32)
    private String senha;

    @Lob
    @Column(name = "ROLE", nullable = false, length = 5)
    private String role;
    
    @Column(name = "LOGIN_VALIDO", nullable = false)
    private boolean loginValido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isLoginValido() {
        return loginValido;
    }

    public void setLoginValido(boolean loginValido) {
        this.loginValido = loginValido;
    }
    
    

}
