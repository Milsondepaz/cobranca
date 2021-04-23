/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.cobranca.service;

import com.milsondev.cobranca.model.StatusTitulo;
import com.milsondev.cobranca.model.Titulo;
import com.milsondev.cobranca.repository.TituloRepository;
import com.milsondev.cobranca.repository.filter.TituloFilter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mein
 */
@Service
public class CadastroTituloService {
    
    @Autowired
    private TituloRepository tituloRepository;
    
    public void salvar(Titulo titulo) {
		try {
			tituloRepository.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long codigo) {
		tituloRepository.deleteById(codigo);
	}

	public String receber(Long codigo) {
		Optional<Titulo> Optitulo = tituloRepository.findById(codigo);		
                Titulo titulo = Optitulo.get();
                titulo.setStatus(StatusTitulo.RECEBIDO);
		tituloRepository.save(titulo);		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	
	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? " " : filtro.getDescricao();                                
		return tituloRepository.findByDescricaoContaining(descricao);
	}
}
