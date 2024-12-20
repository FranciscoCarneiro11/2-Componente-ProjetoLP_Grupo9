
package com.upt.hibernate.proj_9grupo.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.upt.hibernate.proj_9grupo.model.Aluno;
import com.upt.hibernate.proj_9grupo.model.Utilizador;

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

        	aluno.setNome("Teste");
        	aluno.setEmail("teste@gmail.com");
        	aluno.setNumAluno(1); // Certifique-se de que este número não está em uso
        	aluno.setAnoEscolaridade(1);
        	aluno.setTipoUtilizador(Utilizador.TipoUtilizador.aluno);
	        
	        ResponseEntity<Aluno> response = restTemplate.postForEntity(rootAPIURL, aluno, Aluno.class);
	        
	        if (response.getStatusCode().is2xxSuccessful()) {
	            Aluno body = response.getBody();
	            if (body != null) {
	                System.out.println(body.toString());
	            } else {
	                System.out.println("No body.");
	            }
	        } else {
	            System.out.println("Nada encontrado!!");
	        }
	    }
	
	  public void updateAluno(Long id) {
		    Aluno aluno = new Aluno();
		    aluno.setId(id.intValue()); 
		    aluno.setNome("João");
		    aluno.setEmail("joao@gmail.com");
		    aluno.setAnoEscolaridade(12);
		    aluno.setNumAluno(50266);
		    
		    ResponseEntity<Aluno> response = restTemplate.exchange(rootAPIURL + "/" + id, HttpMethod.PUT, new HttpEntity<>(aluno), Aluno.class);
		    
		    if (response.getStatusCode().is2xxSuccessful()) {
		        Aluno updatedAluno = response.getBody();
		        if (updatedAluno != null) {
		            System.out.println("Aluno atualizado: " + updatedAluno.toString());
		        } else {
		            System.out.println("No body.");
		        }
		    } else {
		        System.out.println("Erro ao atualizar aluno: " + response.getStatusCode());
		    }
		}
	
	public void eliminarAluno(Long id) {
	    restTemplate.delete(rootAPIURL + "/" + id.toString());
	    System.out.println("Aluno eliminado com sucesso!");
	}
	
}

