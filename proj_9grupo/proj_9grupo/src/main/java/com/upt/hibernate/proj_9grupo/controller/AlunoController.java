package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public List<Aluno> getAllAlunos() {
	return alunoService.getAllAlunos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable Long id) {
		Optional<Aluno> aluno = alunoService.getAlunoById(id);
		return aluno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Aluno criarAluno(@RequestBody Aluno aluno) {
		return alunoService.criarAluno(aluno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarAluno(@PathVariable Long id) {
		alunoService.eliminarAluno(id);
		return ResponseEntity.noContent().build();
	}
	
	
	//falta criar o "put" para atualizar o aluno
}
