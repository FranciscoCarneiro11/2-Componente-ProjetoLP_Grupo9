package com.upt.hibernate.proj_9grupo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "resposta_quiz")
public class RespostaQuiz {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idAluno")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(nullable = false)
    private int pontuacao;


    // Get's e set's
    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    }
    public Aluno getAluno() { 
    	return aluno; 
    }
    public void setAluno(Aluno aluno) { 
    	this.aluno = aluno; 
    }

    public Quiz getQuiz() { 
    	return quiz; 
    }
    public void setQuiz(Quiz quiz) { 
    	this.quiz = quiz; 
    }

    public int getPontuacao() { 
    	return pontuacao; 
    }
    public void setPontuacao(int pontuacao) { 
    	this.pontuacao = pontuacao; 
    }

}
