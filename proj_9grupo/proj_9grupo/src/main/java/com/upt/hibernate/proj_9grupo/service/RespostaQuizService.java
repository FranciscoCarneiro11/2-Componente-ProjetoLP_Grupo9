package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;
import com.upt.hibernate.proj_9grupo.repository.RespostaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaQuizService {
	private final RespostaQuizRepository respostaquizRepository;
	
	@Autowired
	public RespostaQuizService(RespostaQuizRepository respostaquizRepository) {
		this.respostaquizRepository = respostaquizRepository;
	}
	
	public List<RespostaQuiz> getAllRespostas(){
		return respostaquizRepository.findAll();
	}
	
	public Optional<RespostaQuiz> getRespostasById(Long id) {
		return respostaquizRepository.findById(id);
		}

	public RespostaQuiz criarRespostaQuiz(RespostaQuiz respostaquiz) {
		return respostaquizRepository.save(respostaquiz);
	}
	
	/*public RespostaQuiz atualizarRespostasQuiz(Long id, RespostaQuiz respostaquiz) {
	 * 
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

