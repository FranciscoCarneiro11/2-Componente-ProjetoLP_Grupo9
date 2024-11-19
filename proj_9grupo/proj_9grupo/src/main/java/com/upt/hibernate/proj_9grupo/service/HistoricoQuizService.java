package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.HistoricoQuiz;
import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.repository.HistoricoQuizRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoQuizService {

	private final HistoricoQuizRepository historicoQuizRepository;
	
	@Autowired
	public HistoricoQuizService(HistoricoQuizRepository historicoQuizRepository) {
		this.historicoQuizRepository = historicoQuizRepository;
	}
	
	 public List<HistoricoQuiz> obterHistoricoPorAluno(Aluno aluno) {
		 return historicoQuizRepository.findByAluno(aluno);
	 }

	 public HistoricoQuiz adicionarHistorico(HistoricoQuiz historicoQuiz) {
		 return historicoQuizRepository.save(historicoQuiz);
	 }
	
}
