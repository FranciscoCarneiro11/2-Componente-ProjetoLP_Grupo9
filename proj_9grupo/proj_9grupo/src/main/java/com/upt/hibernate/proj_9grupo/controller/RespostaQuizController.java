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
    public ResponseEntity<List<RespostaQuiz>> getAllRespostas() {
        return ResponseEntity.ok(respostaQuizService.getAllRespostas());
    }
	
	@GetMapping("/aluno/{id}")
	public ResponseEntity<List<RespostaQuiz>> getRespostasByAlunoId(@PathVariable Long id) {
	    List<RespostaQuiz> respostas = respostaQuizService.getRespostasByAlunoId(id);
	    return ResponseEntity.ok(respostas);
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
    public ResponseEntity<RespostaQuiz> criarRespostaQuiz(@RequestBody RespostaQuiz respostaquiz) {
        RespostaQuiz novaResposta = respostaQuizService.criarRespostaQuiz(respostaquiz);
        return ResponseEntity.ok(novaResposta);
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminarResposta(@PathVariable Long id) {
		respostaQuizService.eliminarRespostaQuiz(id);
		return ResponseEntity.noContent().build();
	}
}
