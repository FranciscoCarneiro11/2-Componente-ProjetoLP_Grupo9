package com.upt.hibernate.proj_9grupo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "utilizador")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilizador {
    
	public enum TipoUtilizador {
	    professor,
	    aluno,
	}

	public Utilizador() {
		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_utilizador", nullable = false)
    private TipoUtilizador tipoUtilizador;

    // Get's e set's
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
    
    public TipoUtilizador getTipoUtilizador() {
		return tipoUtilizador;
	}

	public void setTipoUtilizador(TipoUtilizador tipoUtilizador) {
		this.tipoUtilizador = tipoUtilizador;
	}

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}
