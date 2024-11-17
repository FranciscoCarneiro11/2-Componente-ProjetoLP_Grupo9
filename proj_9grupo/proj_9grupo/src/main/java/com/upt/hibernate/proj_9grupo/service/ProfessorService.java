package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

	private final ProfessorRepository professorRepository;
	
	@Autowired
	public ProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public List<Professor> getAllProfessores(){
		return professorRepository.findAll();
	}
	
	public Optional<Professor> getProfessorById(Long id) {
		return professorRepository.findById(id);
	}

	public Professor criarProfessor(Professor professor) {
		professor.setTipoUtilizador(Utilizador.TipoUtilizador.professor);
		return professorRepository.save(professor);
	}
	
	/*public Aluno atualizarProfessor(Long id, Professor professor) {
		professor.setId(id);
		
	}
	*/
	
	public void eliminarProfessor(Long id) {
		if (professorRepository.existsById(id)) {
				professorRepository.deleteById(id);
		} else {
				throw new RuntimeException("Professor não encontrado com o id: "+ id);
		}
	}
	
	
}
