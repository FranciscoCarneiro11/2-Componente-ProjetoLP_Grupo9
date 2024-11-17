package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Pergunta;
import com.upt.hibernate.proj_9grupo.repository.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerguntaService {
	
	private final PerguntaRepository perguntaRepository;
	
	@Autowired
	public PerguntaService(PerguntaRepository perguntaRepository) {
		this.perguntaRepository = perguntaRepository;
	}
	
	public List<Pergunta> getAllPerguntas(){
		return perguntaRepository.findAll();
	}
	
	public Optional<Pergunta> getPerguntaById(Long id) {
		return perguntaRepository.findById(id);
		}

	public Pergunta criarPergunta(Pergunta pergunta) {
		return perguntaRepository.save(pergunta);
	}
	
	/*public Pergunta atualizarPergunta(Long id, Pergunta pergunta) {
	 * 
	}
	*/
	
	public void eliminarPergunta(Long id) {
		if (perguntaRepository.existsById(id)) {
			perguntaRepository.deleteById(id);
		} else {
				throw new RuntimeException("Pergunta não encontrada com o id: "+ id);
		}
	}
	
}
