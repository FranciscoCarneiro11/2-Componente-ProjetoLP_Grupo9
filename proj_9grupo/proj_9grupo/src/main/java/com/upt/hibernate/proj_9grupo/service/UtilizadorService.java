package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.repository.UtilizadorRepository;
import com.upt.hibernate.proj_9grupo.repository.AlunosRepository;
import com.upt.hibernate.proj_9grupo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {

	private final UtilizadorRepository utilizadorRepository;
	@Autowired
	private AlunosRepository alunosRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	public UtilizadorService(UtilizadorRepository utilizadorRepository) {
		this.utilizadorRepository = utilizadorRepository;
	}
	
	public List<Utilizador> getAllUtilizadores(){
		List<Utilizador> utilizador = new ArrayList<>();
		utilizador.addAll(alunosRepository.findAll());
		utilizador.addAll(professorRepository.findAll());
		return utilizador;

	}
	
	public Utilizador getUtilizadoresById(Long id) {
	    Optional<Utilizador> utilizadorOptional = utilizadorRepository.findById(id);
	    if (utilizadorOptional.isPresent()) {
	        return utilizadorOptional.get();
	    }

	    Optional<Aluno> aluno = alunosRepository.findById(id);
	    if (aluno.isPresent()) {
	        return aluno.get();
	    }
	    
	    Optional<Professor> professor = professorRepository.findById(id);
	    if (professor.isPresent()) {
	        return professor.get();
	    }

	    throw new RuntimeException("Utilizador não encontrado com o id: " + id);
	}

	public Utilizador criarUtilizador(Utilizador utilizador) {
		if (utilizador instanceof Aluno) {
		return alunosRepository.save((Aluno) utilizador);
		} else if (utilizador instanceof Professor) {
		return professorRepository.save((Professor) utilizador);
		}
		throw new IllegalArgumentException("Tipo de utilizador desconhecido!!");
		}
	
	public Utilizador updateUtilizador(Long id, Utilizador utilizador) {
		if(utilizador instanceof Aluno) {
			Aluno existingAluno = alunosRepository.findById(id).orElse(null);
			if (existingAluno != null) {
				existingAluno.setNome(((Aluno) utilizador).getNome());
	    		existingAluno.setEmail(((Aluno) utilizador).getEmail());
	    		existingAluno.setAnoEscolaridade(((Aluno) utilizador).getAnoEscolaridade());
	    		existingAluno.setNumAluno(((Aluno) utilizador).getNumAluno());
				return alunosRepository.save(existingAluno);
				}
		
		} else if(utilizador instanceof Utilizador) {
			Professor existingProfessor = professorRepository.findById(id).orElse(null);
			if (existingProfessor != null) {
				existingProfessor.setNome(((Professor) utilizador).getNome());
				existingProfessor.setEmail(((Professor) utilizador).getEmail());
				existingProfessor.setDisciplina(((Professor) utilizador).getDisciplina());
				existingProfessor.setNumProfessor(((Professor) utilizador).getNumProfessor());
				return professorRepository.save(existingProfessor);
				}
		}
		
		return null;
	}
	
	
	public void eliminarUtilizador(Long id) {
		if (alunosRepository.existsById(id)) {
			alunosRepository.deleteById(id);
		}
		if (professorRepository.existsById(id)) {
			professorRepository.deleteById(id);
		}
		
		throw new RuntimeException("Utilizador não encontrado com o id: "+ id);
		
	}
	
}
