package com.upt.hibernate.proj_9grupo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.service.ProfessorService;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping
	public List<Professor> getAllProfessores() {
	return professorService.getAllProfessores();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Professor> getProfessorById(@PathVariable Long id) {
		Optional<Professor> professor = professorService.getProfessorById(id);
		return professor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Professor criarProfessor(@RequestBody Professor professor) {
		return professorService.criarProfessor(professor);
	}
	
	@PutMapping("/{id}")
	 public Professor updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
	 return professorService.updateProfessor(id, professor);
	 }

	
	@DeleteMapping("/{id}")
	public String eliminarProfessor(@PathVariable Long id) {
		professorService.eliminarProfessor(id);
		return "Professor eliminado com sucesso!!";

	}
	

}
