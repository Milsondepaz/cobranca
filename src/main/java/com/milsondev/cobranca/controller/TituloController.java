/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.cobranca.controller;

import com.milsondev.cobranca.model.StatusTitulo;
import com.milsondev.cobranca.model.Titulo;
import com.milsondev.cobranca.repository.TituloRepository;
import com.milsondev.cobranca.repository.filter.TituloFilter;
import com.milsondev.cobranca.service.CadastroTituloService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Mein
 */
@Controller
public class TituloController {

    private static final String INDEX = "index";
    @Autowired
    private CadastroTituloService cadastroTituloService;
    
    @Autowired
    private TituloRepository tituloRepository;

    @RequestMapping("")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView(INDEX);
        mv.addObject(new Titulo());
        return mv;
    }

    @RequestMapping(value = "/novotitulo", method = RequestMethod.POST)
    public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
        
        
        
        if (errors.hasErrors()) {
            return INDEX;
        }
        try {
            cadastroTituloService.salvar(titulo);
            attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            errors.rejectValue("dataVencimento", null, e.getMessage());
            return INDEX;
        }

    }

    @RequestMapping("/titulos")
    public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {        
        //filtro.setDescricao("");        
        //System.out.println(filtro.getDescricao());       
        List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);        
        //String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();        
        //List<Titulo> todosTitulos = tituloRepository.findByDescricaoContaining(descricao);                
        ModelAndView mv = new ModelAndView("PesquisaTitulo");
        mv.addObject("titulos", todosTitulos);
        return mv;
    }

    @RequestMapping("{codigo}")
    public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
        ModelAndView mv = new ModelAndView(INDEX);
        mv.addObject(titulo);
        return mv;
    }

    @RequestMapping(value = "delete/{codigo}", method = RequestMethod.DELETE)
    public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
        
        //System.out.println(codigo);
        
        cadastroTituloService.excluir(codigo);
        attributes.addFlashAttribute("mensagem", "Título excluído com sucesso!");
        return "redirect:/titulos";

    }

    
    // ResponseBody- nao retorna uma view rotorna a mensagem no body da requiscao
    @RequestMapping(value = "receber/{codigo}", method = RequestMethod.PUT)
    public @ResponseBody
    String receber(@PathVariable Long codigo) {
        return cadastroTituloService.receber(codigo);
    }

    @ModelAttribute("todosStatusTitulo")
    public List<StatusTitulo> todosStatusTitulo() {
        return Arrays.asList(StatusTitulo.values());
    }

}
