package com.upt.hibernate.proj_9grupo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upt.hibernate.proj_9grupo.model.Quiz;
import com.upt.hibernate.proj_9grupo.model.RelatorioDesempenho;
import com.upt.hibernate.proj_9grupo.model.RespostaQuiz;
import com.upt.hibernate.proj_9grupo.repository.QuizRepository;
import com.upt.hibernate.proj_9grupo.repository.RespostaQuizRepository;
import com.upt.hibernate.proj_9grupo.service.DesempenhoService;

@RestController
@RequestMapping("/api/desempenho")
public class DesempenhoController {

    @Autowired
    private RespostaQuizRepository respostaQuizRepository;

    @Autowired
    private QuizRepository quizRepository;

    @GetMapping("/{quizId}")
    public ResponseEntity<List<RelatorioDesempenho>> obterDesempenho(@PathVariable Long quizId) {
        Optional<Quiz> quizOpt = quizRepository.findById(quizId);
        if (!quizOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Obtenha as respostas do quiz para todos os alunos
        List<RespostaQuiz> respostas = respostaQuizRepository.findByQuiz(quizOpt.get());
        if (respostas.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList()); // Retorna uma lista vazia se não houver respostas
        }

        // Processar as respostas e gerar o relatório de desempenho
        List<RelatorioDesempenho> relatorio = new ArrayList<>();
        for (RespostaQuiz resposta : respostas) {
            // Aqui você pode calcular a pontuação e outras métricas
        	RelatorioDesempenho desempenho = new RelatorioDesempenho();
            desempenho.setAluno(resposta.getAluno());
            desempenho.setPontuacao(resposta.getPontuacao());
            relatorio.add(desempenho);
        }

        return ResponseEntity.ok(relatorio);
    }
}
