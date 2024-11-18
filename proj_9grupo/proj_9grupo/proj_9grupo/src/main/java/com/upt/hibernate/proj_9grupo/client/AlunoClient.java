/*
package com.upt.hibernate.proj_9grupo.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.upt.hibernate.proj_9grupo.model.Aluno;

public class AlunoClient {

	private RestTemplate restTemplate = new RestTemplate();
	
	private String rootAPIURL = "http://localhost:8080/api/aluno";
	
	public void getAlunoById(Long id) {
		ResponseEntity <Aluno> response = restTemplate.getForEntity(rootAPIURL + "/" + id.toString(), Aluno.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Aluno body = response.getBody();
			if(body != null) {
				System.out.println(body.toString());
			} else {
				System.out.println("No body.");
			}
		} else {
			System.out.println("Nada encontrado!!");
		}
	}
	
	public void getAllAlunos() {
		ResponseEntity<Aluno[]> response = restTemplate.getForEntity(rootAPIURL, Aluno[].class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Aluno[] alunos = response.getBody();
			if(alunos != null) {
				for(Aluno aluno : alunos) {
					System.out.println(aluno.toString());
				}
			} else {
				System.out.println("No body!");
			}
		} else {
			System.out.println("Nada encontrado!!");
		}
	}
	
	public void criarAluno() {
		Aluno aluno = new Aluno();
		
		aluno.setNome("Francisco");
		aluno.setEmail("francisco@gmail.com");
		aluno.setAnoEscolaridade(12);
		aluno.setNumAluno(420);
		
		ResponseEntity <Aluno> response = restTemplate.postForEntity(rootAPIURL, aluno, Aluno.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Aluno body = response.getBody();
			if(body != null) {
				System.out.println(body.toString());
			} else {
				System.out.println("No body.");
			}
		} else {
			System.out.println("Nada encontrado!!");
		}
	}
	
	public void updateAluno() {
		Aluno aluno = new Aluno();
		
		aluno.setId(1);
		aluno.setNome("Joao");
		aluno.setEmail("joao@gmail.com");
		aluno.setAnoEscolaridade(12);
		aluno.setNumAluno(421);
		
		ResponseEntity <Aluno> response = restTemplate.postForEntity(rootAPIURL, aluno, Aluno.class);
		
		if(response.getStatusCode().is2xxSuccessful()) {
			Aluno body = response.getBody();
			if(body != null) {
				System.out.println(body.toString());
			} else {
				System.out.println("No body.");
			}
		} else {
			System.out.println("Nada encontrado!!");
		}
	}
	
}
*/
