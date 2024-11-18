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
		if(pergunta.getQuestao() == null || pergunta.getQuestao().isEmpty()) {
			throw new RuntimeException("Não pode existir uma questão vazia!!!");
		}
		
		if(pergunta.getOpcaoA() == null || pergunta.getOpcaoA().isEmpty() || pergunta.getOpcaoB() == null || pergunta.getOpcaoB().isEmpty() ||
	       pergunta.getOpcaoC() == null || pergunta.getOpcaoC().isEmpty() || pergunta.getOpcaoD() == null || pergunta.getOpcaoD().isEmpty()) {
			throw new RuntimeException("Não podem existir opções vazias! Todas as opções têm de ser preenchidas!!");
		}
		
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
