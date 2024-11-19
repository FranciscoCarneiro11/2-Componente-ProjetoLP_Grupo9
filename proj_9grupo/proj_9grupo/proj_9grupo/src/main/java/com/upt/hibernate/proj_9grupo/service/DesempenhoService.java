package com.upt.hibernate.proj_9grupo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.model.RelatorioDesempenho;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;
import com.upt.hibernate.proj_9grupo.repository.RespostaQuizRepository;

@Service
public class DesempenhoService {
	
	private final RespostaQuizRepository respostaQuizRepository;
	private final QuizRepository quizRepository; 
	
	@Autowired
    public DesempenhoService(RespostaQuizRepository respostaQuizRepository, QuizRepository quizRepository) {
        this.respostaQuizRepository = respostaQuizRepository;
        this.quizRepository = quizRepository;
    }
	
	public List<RelatorioDesempenho> criarRelatorioDesempenho(Long quizId) {
	    Quiz quiz = quizRepository.findById(quizId)
	            .orElseThrow(() -> new RuntimeException("Quiz não encontrado!"));

	    List<RespostaQuiz> respostas = respostaQuizRepository.findByQuiz(quiz);
	    System.out.println("Respostas encontradas: " + respostas.size());
	    
	    respostas.sort(Comparator.comparingInt(RespostaQuiz::getPontuacao).reversed());

	    List<RelatorioDesempenho> relatorio = new ArrayList<>();
	    for (int i = 0; i < respostas.size(); i++) {
	        RespostaQuiz resposta = respostas.get(i);
	        if (resposta.getAluno() != null) {
	            relatorio.add(new RelatorioDesempenho(resposta.getPontuacao(), i + 1));
	        } else {
	            System.out.println("Aluno não encontrado para a resposta: " + resposta.getId());
	        }
	    }
	    return relatorio;
	}
}
