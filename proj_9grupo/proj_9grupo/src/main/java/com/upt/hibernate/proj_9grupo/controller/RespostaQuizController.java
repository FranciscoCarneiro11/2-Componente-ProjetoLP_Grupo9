package com.upt.hibernate.proj_9grupo.controller;

import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.service.RespostaQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resposta_quiz")
public class RespostaQuizController {

	@Autowired
	private RespostaQuizService respostaQuizService;
	
	@GetMapping
	public List<RespostaQuiz> getAllRespostas() {
	return respostaQuizService.getAllRespostas();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RespostaQuiz> getRespostaById(@PathVariable Long id) {
		Optional<RespostaQuiz> resposta = respostaQuizService.getRespostasById(id);
		return resposta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	/*
	 * @PutMapping("/{id}")
    public ResponseEntity<RespostaQuiz> atualizarRespostaQuiz(@PathVariable Long id, @RequestBody RespostaQuiz resposta) {
        RespostaQuiz respostaAtualizada = respostaQuizService.atualizarRespostaQuiz(id, resposta);
        if (respostaAtualizada != null) {
            return ResponseEntity.ok(respostaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	 */
	@PostMapping
	public RespostaQuiz criarRespostaQuiz(@RequestBody RespostaQuiz resposta) {
		return respostaQuizService.criarRespostaQuiz(resposta);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarResposta(@PathVariable Long id) {
		respostaQuizService.eliminarRespostaQuiz(id);
		return ResponseEntity.noContent().build();
	}
}
