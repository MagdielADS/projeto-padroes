/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.entidades;

import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.entidades.TipoSala;

/**
 *
 * @author Magdiel Ildefonso
 */
public class SalaBuilder {
    private final int capacidade;
    private final TipoSala tipoSala;
    private String apelido;
    private final String identicacao;
    
    public SalaBuilder(String predio, int numero, int capacidade, TipoSala tipoSala){
        this.capacidade = capacidade;
        this.tipoSala = tipoSala;
        this.identicacao = setIdentificacao(predio, numero);
    }
    
    public SalaBuilder(String identificacao, int capacidade, TipoSala tipoSala){
        this.capacidade = capacidade;
        this.tipoSala = tipoSala;
        this.identicacao = identificacao;
    }
    
    
    private String setIdentificacao(String predio, int numero){
        String[] auxPredio = predio.split(" ");
        String id = "";
        
        if(auxPredio.length > 1){
            for(int i = 0; i < auxPredio.length; i++){
                id += auxPredio[i].substring(0, 1);
            }
            id += numero;
        }
        return id;
    }
    
    public SalaBuilder comApelido(String apelido){
        this.apelido = apelido;
        return this;
    }
    
    public Sala construa(){
        return new Sala(identicacao, capacidade, apelido, tipoSala);
    }
}
