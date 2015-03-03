/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.testes;

import br.edu.ifpb.padroes.entidades.Evento;
import br.edu.ifpb.padroes.entidades.StatusEvento;
import br.edu.ifpb.padroes.fachada.FachadaEvento;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magdiel Ildefonso
 */
public class LoaderEventoDAO {

    public static void main(String[] args) throws SQLException {
        FachadaEvento fachada = new FachadaEvento();

        //CADASTRAR
        try {
            Evento e = new Evento();
            e.setDescricao("Competição de prgramação entre equipes");
            e.setDtFim(new Date());
            e.setDtInicio(new Date());
            e.setNome("Olimpíadas de programação");
            e.setNomeResponsavel("Diogo");
            e.setStatus(StatusEvento.PEDENTE_LOCAL);
            e.setNumeroRepeticoes(1);
            e.setQtde_participantes(30);
            fachada.cadastrar(e);

            e.setDescricao("Primeira aula do período, apresentação do curso aos feras");
            e.setDtFim(new Date());
            e.setDtInicio(new Date());
            e.setNome("Apresentação do curso");
            e.setNomeResponsavel("Fábio");
            e.setStatus(StatusEvento.PEDENTE_LOCAL);
            e.setNumeroRepeticoes(1);
            e.setQtde_participantes(60);
            fachada.cadastrar(e);
        } catch (SQLException ex) {
            Logger.getLogger(LoaderEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ATUALIZAR
        //Evento e2 = fachada.listarPorId(5);

        //e2.setNomeResponsavel("José da Silva Nobrega");
        //e2.setNome("POP");
        //fachada.atualizar(e2);
        //DELETAR
        //fachada.deletar(e2);
        //LISTAR TODOS OS EVENTOS
        for (Evento ev : fachada.listar()) {
            System.out.println(ev);
        }
    }
}
