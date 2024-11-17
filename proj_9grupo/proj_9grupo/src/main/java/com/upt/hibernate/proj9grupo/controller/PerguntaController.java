package com.upt.hibernate.proj9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Pergunta;
import com.upt.hibernate.proj_9grupo.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pergunta")
public class PerguntaController {

	@Autowired
	private PerguntaService perguntaService;
	
	@GetMapping
	public List<Pergunta> getAllPerguntas() {
	return perguntaService.getAllPerguntas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pergunta> getPerguntaById(@PathVariable Long id) {
		Optional<Pergunta> pergunta = perguntaService.getPerguntaById(id);
		return pergunta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Pergunta criarPergunta(@RequestBody Pergunta pergunta) {
		return perguntaService.criarPergunta(pergunta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarPergunta(@PathVariable Long id) {
		perguntaService.eliminarPergunta(id);
		return ResponseEntity.noContent().build();
	}
	
}
