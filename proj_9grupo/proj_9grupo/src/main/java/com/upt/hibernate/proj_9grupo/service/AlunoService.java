package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.repository.AlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AlunoService {

    private final AlunosRepository alunosRepository;

    @Autowired
    public AlunoService(AlunosRepository alunosRepository) {
        this.alunosRepository = alunosRepository;
    }

    public List<Aluno> getAllAlunos() {
        return alunosRepository.findAll();
    }

    public Optional<Aluno> getAlunoById(Long id) {
        return alunosRepository.findById(id);
    }

    public Aluno criarAluno(Aluno aluno) {
    	aluno.setTipoUtilizador(Utilizador.TipoUtilizador.aluno); 
        return alunosRepository.save(aluno);
    }

    public void eliminarAluno(Long id) {
        if (alunosRepository.existsById(id)) {
            alunosRepository.deleteById(id);
        } else {
            throw new RuntimeException("Aluno não encontrado com o id: " + id);
        }
    }
}
