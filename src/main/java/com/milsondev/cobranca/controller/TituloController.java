/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.cobranca.controller;

import com.milsondev.cobranca.model.Titulo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mein
 */

@Controller
public class TituloController {
    
    @RequestMapping("")
    public String novo(){
        
        return "CadastroTitulo";
    }
    
    @RequestMapping(value = "/novotitulo", method = RequestMethod.POST)
    public String salvar(Titulo titulo ) {
        
        System.out.println(titulo.getDescricao());     
        
        return "CadastroTitulo";
    }
    
    
    
}
