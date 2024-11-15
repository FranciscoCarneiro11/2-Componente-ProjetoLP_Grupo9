package com.upt.hibernate.proj_9grupo.repository;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunosRepository extends JpaRepository<Aluno, Long> {
	
	
	
	
}
