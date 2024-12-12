package upt.projeto.service;

import upt.projeto.model.Login;
import upt.projeto.model.RespostaLogin;
import upt.projeto.model.Utilizador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class LoginService {
	private static final String BASE_URL = "http://localhost:8080/api";
    private RestTemplate restTemplate = new RestTemplate();
    
    public boolean login(String email, String password) {
        String url = BASE_URL + "/login"; 
        Login loginRequest = new Login(email, password);

        ResponseEntity<RespostaLogin> response = restTemplate.postForEntity(url, loginRequest, RespostaLogin.class);

        if (response.getStatusCode().is2xxSuccessful()) {
        	RespostaLogin loginResponse = response.getBody();
            return loginResponse != null && loginResponse.isSuccess(); 
        } else {
            System.out.println("Login falhou: " + response.getStatusCode());
            return false; 
        }
    }
    
    public Utilizador getUtilizador(String email) {
        String url = BASE_URL + "/utilizador/email/" + email; 
        ResponseEntity<Utilizador> response = restTemplate.getForEntity(url, Utilizador.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); 
        } else {
            System.out.println("Falha ao obter utilizador: " + response.getStatusCode());
            return null; 
        }
    }
    
}
