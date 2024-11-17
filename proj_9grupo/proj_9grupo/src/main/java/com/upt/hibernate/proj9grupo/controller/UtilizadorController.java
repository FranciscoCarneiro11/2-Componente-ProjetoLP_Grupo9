package com.upt.hibernate.proj9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.service.UtilizadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilizador")
public class UtilizadorController {

	@Autowired
	private UtilizadorService utilizadorService;
	
	@GetMapping
	public List<Utilizador> getAllUtilizadores() {
	return utilizadorService.getAllUtilizadores();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Utilizador> getUtilizadorById(@PathVariable Long id) {
		Optional<Utilizador> utilizador = utilizadorService.getUtilizadoresById(id);
		return utilizador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Utilizador criarUtilizador(@RequestBody Utilizador utilizador) {
		return utilizadorService.criarUtilizador(utilizador);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarUtilizador(@PathVariable Long id) {
		utilizadorService.eliminarUtilizador(id);
		return ResponseEntity.noContent().build();
	}
}
