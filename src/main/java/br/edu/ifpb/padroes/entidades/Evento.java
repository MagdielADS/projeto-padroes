/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.entidades;

import java.util.Date;

/**
 *
 * @author Magdiel Ildefonso
 *
 */
/**
 * A classe Evento é uma abstração para os eventos criados no sistema
 */
public class Evento {

    /**
     * O id do evento será gerado automaticamente no banco de dados
     */
    private int id;
    private String nome;
    private String descricao;
    private Date dtInicio;
    private Date dtFim;
    private String nomeResponsavel;
    /**
     * O numero de repetições define quantas vezes o evento deve sr repetido na
     * semana
     */
    private int numeroRepeticoes;
    /**
     * Status: PENDENTE DE LOCAL, ALOCADO, REALIZADO E CANCELADO
     */
    private StatusEvento status;
    /**
     * A quantidade de participantes é necessário para assim serem apresentadas
     * as salas que satisfazem os requisitos de evento
     */
    private int qtde_participantes;
    private Sala sala;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public int getNumeroRepeticoes() {
        return numeroRepeticoes;
    }

    public void setNumeroRepeticoes(int numeroRepeticoes) {
        this.numeroRepeticoes = numeroRepeticoes;
    }

    public StatusEvento getStatus() {
        return status;
    }

    public void setStatus(StatusEvento status) {
        this.status = status;
    }

    public int getQtde_participantes() {
        return qtde_participantes;
    }

    public void setQtde_participantes(int qtde_participantes) {
        this.qtde_participantes = qtde_participantes;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dtInicio=" + dtInicio + ", dtFim=" + dtFim + ", nomeResponsavel=" + nomeResponsavel + ", numeroRepeticoes=" + numeroRepeticoes + ", status=" + status + ", qtde_participantes=" + qtde_participantes + ", sala=" + sala + '}';
    }
}
