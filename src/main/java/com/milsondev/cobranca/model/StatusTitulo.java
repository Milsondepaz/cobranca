/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milsondev.cobranca.model;

/**
 *
 * @author Mein
 */
public enum StatusTitulo {

    PENDENTE("Pendente"),
    RECEBIDO("Recebido");
    
    private String descricao;

    StatusTitulo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
