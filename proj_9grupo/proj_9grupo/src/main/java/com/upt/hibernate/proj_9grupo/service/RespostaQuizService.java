package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.HistoricoQuiz;
import com.upt.hibernate.proj_9grupo.model.Pergunta;
import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.repository.AlunosRepository;
import com.upt.hibernate.proj_9grupo.repository.HistoricoQuizRepository;
import com.upt.hibernate.proj_9grupo.repository.PerguntaRepository;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;
import com.upt.hibernate.proj_9grupo.repository.RespostaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaQuizService {
	private final RespostaQuizRepository respostaquizRepository;
	private final PerguntaRepository perguntaRepository;
	private final QuizRepository quizRepository;
	private final HistoricoQuizRepository historicoQuizRepository;
	private final AlunosRepository alunoRepository;
	@Autowired
	public RespostaQuizService(RespostaQuizRepository respostaquizRepository, PerguntaRepository perguntaRepository, QuizRepository quizRepository, HistoricoQuizRepository historicoQuizRepository, AlunosRepository alunoRepository) {
		this.respostaquizRepository = respostaquizRepository;
		this.perguntaRepository = perguntaRepository;
		this.quizRepository = quizRepository;
		this.historicoQuizRepository = historicoQuizRepository;
		this.alunoRepository = alunoRepository;
	}
	
	public List<RespostaQuiz> getAllRespostas(){
		return respostaquizRepository.findAll();
	}
	
	public List<RespostaQuiz> getRespostasByAlunoId(Long alunoId) {
	    Aluno aluno = alunoRepository.findById(alunoId).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
	    return respostaquizRepository.findByAluno(aluno);
	}

	public RespostaQuiz criarRespostaQuiz(RespostaQuiz respostaquiz) {
		Optional<Aluno> alunoOpt = alunoRepository.findById((long) respostaquiz.getAluno().getId());
	    if (!alunoOpt.isPresent()) {
	        throw new RuntimeException("Aluno não encontrado com ID: " + respostaquiz.getAluno().getId());
	    }

	    // Verifique se o quiz existe
	    Optional<Quiz> quizOpt = quizRepository.findById((long) respostaquiz.getQuiz().getId());
	    if (!quizOpt.isPresent()) {
	        throw new RuntimeException("Quiz não encontrado com ID: " + respostaquiz.getQuiz().getId());
	    }
	    
	    Quiz quiz = quizOpt.get();
        
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

        HistoricoQuiz historico = new HistoricoQuiz();
        historico.setAluno(respostaquiz.getAluno());
        historico.setQuiz(respostaquiz.getQuiz());
        historico.setPontuacao(pontuacao); 
        historico.setDataRealizacao(LocalDateTime.now());        
        historicoQuizRepository.save(historico);
        
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

