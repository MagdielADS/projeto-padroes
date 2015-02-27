/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.entidades;

/**
 *
 * @author Magdiel Ildefonso
 */
public final class Sala{
    private final String identicacao;
    private final int capacidade;
    private final String apelido;
    private final TipoSala tipoSala;
    
    protected Sala(String identificacao, int capacidade, String apelido, TipoSala tipoSala){
        this.apelido = apelido;
        this.capacidade = capacidade;
        this.identicacao = identificacao;
        this.tipoSala = tipoSala;
    }

    public String getIdenticacao() {
        return identicacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getApelido() {
        return apelido;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }
}
