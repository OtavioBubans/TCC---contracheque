package br.com.crescer.contra.cheque.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {
    
    @Id
    @Column(name="ID", nullable=false)
    private Long id;
    
    @Column(name="EMAIL", nullable=false)
    private String email;
     
    @Column(name="SENHA", nullable=false)
    private String senha;
    
    
}
