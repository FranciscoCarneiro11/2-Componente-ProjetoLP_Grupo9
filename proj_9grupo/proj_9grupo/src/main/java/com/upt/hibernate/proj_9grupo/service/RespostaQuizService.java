package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Pergunta;
import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.repository.PerguntaRepository;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;
import com.upt.hibernate.proj_9grupo.repository.RespostaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaQuizService {
	private final RespostaQuizRepository respostaquizRepository;
	private final PerguntaRepository perguntaRepository;
	private final QuizRepository quizRepository;
	
	
	@Autowired
	public RespostaQuizService(RespostaQuizRepository respostaquizRepository, PerguntaRepository perguntaRepository, QuizRepository quizRepository) {
		this.respostaquizRepository = respostaquizRepository;
		this.perguntaRepository = perguntaRepository;
		this.quizRepository = quizRepository;
	}
	
	public List<RespostaQuiz> getAllRespostas(){
		return respostaquizRepository.findAll();
	}
	
	public Optional<RespostaQuiz> getRespostasById(Long id) {
		return respostaquizRepository.findById(id);
		}

	public RespostaQuiz criarRespostaQuiz(RespostaQuiz respostaquiz) {
        Quiz quiz = respostaquiz.getQuiz();
        if (quiz == null || !quizRepository.existsById((long) quiz.getId())) {
            throw new RuntimeException("Quiz não encontrado!");
        }
        
        List<Pergunta> perguntas = perguntaRepository.findByQuiz(quiz); 

        int pontuacao = 0;

        
        for (int i = 0; i < perguntas.size(); i++) {
            if (i < respostaquiz.getRespostas().size()) {
                String respostaDada = respostaquiz.getRespostas().get(i);
                if (respostaDada != null && respostaDada.equals(perguntas.get(i).getRespostaCorreta())) {
                    pontuacao++;
                }
            }
        }

        respostaquiz.setPontuacao(pontuacao);

        
        if (respostaquiz.getAluno() == null) {
            throw new RuntimeException("O aluno não pode ser nulo!");
        }
        if (respostaquiz.getQuiz() == null) {
            throw new RuntimeException("O quiz não pode ser nulo!");
        }
        if (respostaquiz.getPontuacao() < 0) {
            throw new RuntimeException("A pontuação não pode ser menor que 0 !");
        }

        return respostaquizRepository.save(respostaquiz);
    }

	
	/*
	 * public RespostaQuiz atualizarRespostaQuiz(Long id, RespostaQuiz resposta) {
        Optional<RespostaQuiz> respostaExistente = respostaQuizRepository.findById(id);
        if (respostaExistente.isPresent()) {
            RespostaQuiz respostaAtualizada = respostaExistente.get();
            respostaAtualizada.setRespostas(resposta.getRespostas()); 
            // Aqui você pode recalcular a pontuação, se necessário
            return respostaQuizRepository.save(respostaAtualizada);
        }
        return null; 
    }
	 */
	
	
	public void eliminarRespostaQuiz(Long id) {
		if (respostaquizRepository.existsById(id)) {
				respostaquizRepository.deleteById(id);
		} else {
				throw new RuntimeException("Resposta não encontrada com o id: "+ id);
		}
	}
	
}

