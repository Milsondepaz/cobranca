/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.cobranca.controller;

import com.milsondev.cobranca.model.StatusTitulo;
import com.milsondev.cobranca.model.Titulo;
import com.milsondev.cobranca.repository.TituloRepository;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Mein
 */
@Controller
public class TituloController {

    @Autowired
    private TituloRepository tituloRepository;

    @RequestMapping("")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("CadastroTitulo");
        return mv;
    }

    @RequestMapping(value = "/novotitulo", method = RequestMethod.POST)
    public ModelAndView salvar(Titulo titulo) {
        tituloRepository.save(titulo);
        ModelAndView mv = new ModelAndView("CadastroTitulo");
        mv.addObject("mensagem", "Titulo salvo com sucesso!");

        return mv;
    }
    
    @RequestMapping("/titulos")
    public ModelAndView pesquisar(){
        List <Titulo> todosTitulos = tituloRepository.findAll();
        ModelAndView mv = new ModelAndView("PesquisaTitulo");
        mv.addObject("titulos", todosTitulos);
        return mv;
    }
    
    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo () {
        return Arrays.asList(StatusTitulo.values());
    }

}
