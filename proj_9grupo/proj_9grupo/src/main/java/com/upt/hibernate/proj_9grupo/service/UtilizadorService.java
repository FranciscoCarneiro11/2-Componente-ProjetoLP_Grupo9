package com.upt.hibernate.proj_9grupo.service;

import com.upt.hibernate.proj_9grupo.model.Utilizador;
import com.upt.hibernate.proj_9grupo.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorService {

	private final UtilizadorRepository utilizadorRepository;
	
	@Autowired
	public UtilizadorService(UtilizadorRepository utilizadorRepository) {
		this.utilizadorRepository = utilizadorRepository;
	}
	
	public List<Utilizador> getAllUtilizadores(){
		return utilizadorRepository.findAll();
	}
	
	public Optional<Utilizador> getUtilizadoresById(Long id) {
		return utilizadorRepository.findById(id);
		}

	public Utilizador criarUtilizador(Utilizador utilizador) {
		if (utilizador.getNome() == null || utilizador.getNome().isEmpty()) {
			throw new RuntimeException("O nome do utilizador não pode ser vazio!");
			}

		if (utilizador.getEmail() == null || utilizador.getEmail().isEmpty()) {
			throw new RuntimeException("O email do utilizador não pode ser vazio!");
			}

		if (utilizadorRepository.existsByEmail(utilizador.getEmail())) {
			throw new RuntimeException("O email já está a ser utilizado! Por favor escolha outro.");
		 	}
		
		return utilizadorRepository.save(utilizador);
	}
	
	/*public Utilizador atualizarUtilizadores(Long id, Utilizador utilizador) {
	 * 
	}
	*/
	
	public void eliminarUtilizador(Long id) {
		if (utilizadorRepository.existsById(id)) {
				utilizadorRepository.deleteById(id);
		} else {
				throw new RuntimeException("Utilizador não encontrada com o id: "+ id);
		}
	}
	
}
