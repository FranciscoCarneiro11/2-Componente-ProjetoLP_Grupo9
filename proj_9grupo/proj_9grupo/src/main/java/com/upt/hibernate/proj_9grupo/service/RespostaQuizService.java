package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Pergunta;
import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.repository.PerguntaRepository;
import com.upt.hibernate.proj_9grupo.repository.RespostaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaQuizService {
	private final RespostaQuizRepository respostaquizRepository;
	private final PerguntaRepository perguntaRepository;
	
	@Autowired
	public RespostaQuizService(RespostaQuizRepository respostaquizRepository, PerguntaRepository perguntaRepository) {
		this.respostaquizRepository = respostaquizRepository;
		this.perguntaRepository = perguntaRepository;
	}
	
	public List<RespostaQuiz> getAllRespostas(){
		return respostaquizRepository.findAll();
	}
	
	public Optional<RespostaQuiz> getRespostasById(Long id) {
		return respostaquizRepository.findById(id);
		}

	public RespostaQuiz criarRespostaQuiz(RespostaQuiz respostaquiz) {
        
        Quiz quiz = respostaquiz.getQuiz();
		List<Pergunta> perguntas = perguntaRepository.findByQuiz(quiz); 

        int pontuacao = 0;

        for (Pergunta pergunta : perguntas) {
            String respostaDada = respostaquiz.getRespostas().get(pergunta.getId()); 
            if (respostaDada != null && respostaDada.equals(pergunta.getRespostaCorreta())) { 
                pontuacao++; 
            }
        }

        respostaquiz.setPontuacao(pontuacao); 

        // Verificar se a resposta é válida
        
        if (respostaquiz.getAluno() == null) {
            throw new RuntimeException("O aluno não pode ser nulo!");
        }

        if (respostaquiz.getQuiz() == null) {
            throw new RuntimeException("O quiz não pode ser nulo!");
        }

        if (respostaquiz.getPontuacao() < 0) {
            throw new RuntimeException("A pontuação não pode ser menor que 0.");
        }

        if (respostaquizRepository.existsByAlunoAndQuiz(respostaquiz.getAluno(), respostaquiz.getQuiz())) {
            throw new RuntimeException("O aluno já respondeu a este quiz!!");
        }

        
        return respostaquizRepository.save(respostaquiz);
    }
	
	/*
	 * public RespostaQuiz atualizarRespostaQuiz(Long id, RespostaQuiz resposta) {
        Optional<RespostaQuiz> respostaExistente = respostaQuizRepository.findById(id);
        if (respostaExistente.isPresent()) {
            RespostaQuiz respostaAtualizada = respostaExistente.get();
            respostaAtualizada.setRespostas(resposta.getRespostas()); // Atualizando as respostas
            // Aqui você pode recalcular a pontuação, se necessário
            return respostaQuizRepository.save(respostaAtualizada);
        }
        return null; // ou lançar uma exceção, se preferir
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

