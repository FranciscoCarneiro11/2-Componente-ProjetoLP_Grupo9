package com.upt.hibernate.proj_9grupo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pergunta")
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String questao;

    @Column(nullable = false)
    private String opcaoA; 

    @Column(nullable = false)
    private String opcaoB;

    @Column(nullable = false)
    private String opcaoC;

    @Column(nullable = false)
    private String opcaoD;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    // Get's e set's
    public int getId() { 
    	return id; 
    }
    public void setId(int id) { 
    	this.id = id; 
    }

    public String getQuestao() { 
    	return questao; 
    }
    public void setQuestao(String questao) { 
    	this.questao = questao; 
    }

    public String getOpcaoA() { 
    	return opcaoA; 
    }
    public void setOpcaoA(String opcaoA) { 
    	this.opcaoA = opcaoA; 
    }

    public String getOpcaoB() { 
    	return opcaoB; 
    }
    public void setOpcaoB(String opcaoB) { 
    	this.opcaoB = opcaoB; 
    }
    
    public String getOpcaoC() { 
    	return opcaoC; 
    }
    public void setOpcaoC(String opcaoC) { 
    	this.opcaoC = opcaoC; 
    }

    public String getOpcaoD() { 
    	return opcaoD; 
    }
    public void setOpcaoD(String opcaoD) { 
    	this.opcaoD = opcaoD; 
    }

    public Quiz getQuiz() { 
    	return quiz; 
    }
    public void setQuiz(Quiz quiz) { 
    	this.quiz = quiz; 
    }
}
