/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.cobranca.repository;

import com.milsondev.cobranca.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Mein
 */
public interface TituloRepository extends JpaRepository<Titulo, Long>{
    
    
    
}
