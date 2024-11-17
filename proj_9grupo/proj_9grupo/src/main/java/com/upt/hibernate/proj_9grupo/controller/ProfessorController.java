package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Professor;
import com.upt.hibernate.proj_9grupo.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarProfessor(@PathVariable Long id) {
		professorService.eliminarProfessor(id);
		return ResponseEntity.noContent().build();
	}
	
	
	//falta criar o "put" para atualizar o professor
}
