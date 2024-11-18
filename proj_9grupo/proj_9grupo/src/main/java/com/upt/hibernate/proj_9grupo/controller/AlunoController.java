package com.upt.hibernate.proj_9grupo.controller;

import java.util.List;
import java.util.Optional;

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
import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.service.AlunoService;



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
	
	@PutMapping("/{id}")
	 public Aluno updateAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
	 return alunoService.updateAluno(id, aluno);
	 }
	
	@DeleteMapping("/{id}")
	public String eliminarAluno(@PathVariable Long id) {
		alunoService.eliminarAluno(id);
		return "Aluno eliminado com sucesso!!";

	}
	

}
