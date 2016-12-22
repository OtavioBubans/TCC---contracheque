
package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author matha
 */
@Entity
@Table(name = "USUARIO")
@XmlRootElement
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Long idUsuario;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "SENHA")
    private String senha;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "FUNCAO")
    private String funcao;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOGINS_SUSPEITOS")
    private Integer loginsSuspeitos;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario")
    private Colaborador colaborador;

    public Usuario() {
    }

    public Usuario(Long id, String email, String senha, String funcao, Integer loginsSuspeitos) {
        this.idUsuario = id;
        this.email = email;
        this.senha = senha;
        this.funcao = funcao;
        this.loginsSuspeitos = loginsSuspeitos;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Integer getLoginsSuspeitos() {
        return loginsSuspeitos;
    }

    public void setLoginsSuspeitos(Integer loginsSuspeitos) {
        this.loginsSuspeitos = loginsSuspeitos;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public boolean equals(Usuario usuario) {
        if (this == usuario) {
            return true;
        }
        if (usuario == null) {
            return false;
        }
        if (!this.email.equals(usuario.email)) {
            return false;
        }
        if (!this.senha.equals(usuario.senha)) {
            return false;
        }
        if (!this.funcao.equals(usuario.funcao)) {
            return false;
        }
        if (!this.idUsuario.equals(usuario.idUsuario)) {
            return false;
        }
        return this.loginsSuspeitos.equals(usuario.loginsSuspeitos);
    }
}
