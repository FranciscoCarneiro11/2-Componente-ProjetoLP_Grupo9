package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

	private final QuizRepository quizRepository;
	
	@Autowired
	public QuizService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}
	
	public List<Quiz> getAllQuizzes(){
		return quizRepository.findAll();
	}
	
	public Optional<Quiz> getQuizById(Long id) {
		return quizRepository.findById(id);
		}

	public Quiz criarQuiz(Quiz quiz) {
		return quizRepository.save(quiz);
	}
	
	/*public Quiz atualizarQuiz(Long id, Quiz quiz) {
	 * 
	}
	*/
	
	public void eliminarQuiz(Long id) {
		if (quizRepository.existsById(id)) {
				quizRepository.deleteById(id);
		} else {
				throw new RuntimeException("Quiz não encontrado com o id: "+ id);
		}
	}
	
}
	
	
	
	

