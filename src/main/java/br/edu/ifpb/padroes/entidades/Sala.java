/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.entidades;

/**
 *
 * @author Magdiel Ildefonso
 *
 *
 */
/**
 * A classe Sala é uma abstração para a salas de aulas que serão alocadas aos
 * eventos
 *
 */
public class Sala {

    /**
     * A identificação dos eventos é feita através da composição entre as
     * iniciais dos nomes do prédio e número da sala
     */
    private String identicacao;
    /**
     * A capacidade definem a capacidade de pessoas na sala
     */
    private int capacidade;
    private String apelido;
    /**
     * TIPO DE SALA: AULA NORMAL, AULA INTELIGENTE, LABORATORIO, CONFERENCIA,
     * VIDEO-CONFERENCIA
     */
    private TipoSala tipoSala;

    public Sala() {

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

    /**
     * Esse método cria a identificação da sala, o mesmo recebe o nome do prédio
     * e o número da sala, com isso é gerado a identificação, são retiradas as
     * inicias do nome do prédio e o número da sala, a combinação dos mesmo cria
     * a identificação.
     *
     * @param predio o nome do prédio onde está a sala
     * @param numero número da sala
     */
    public void setIdenticacao(String predio, String numero) {
        String[] aux = predio.split(" ");
        this.identicacao = "";
        for (int i = 0; i < aux.length; i++) {
            if (aux[i].substring(0, 1).equals(aux[i].substring(0, 1).toUpperCase())) {
                this.identicacao += aux[i].substring(0, 1);
            }
        }
        this.identicacao += numero;

    }

    public void setIdenticacao(String identificacao) {
        this.identicacao = identificacao;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    @Override
    public String toString() {
        return "Sala{" + "identicacao=" + identicacao + ", capacidade=" + capacidade + ", apelido=" + apelido + ", tipoSala=" + tipoSala + '}';
    }

}
