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
        Evento e = new Evento();
        e.setDescricao("Corrida de pessoas");
        e.setDtFim(new Date());
        e.setDtInicio(new Date());
        e.setNome("Corrida de pessoas normais");
        e.setNomeResponsavel("Sayanne de Francisco da Loja de Celular, Francell");
        e.setStatus(StatusEvento.PEDENTE_LOCAL);
        e.setDtInicio(new Date());
        e.setDtFim(new Date());
        e.setNumeroRepeticoes(10);
        e.setQtde_participantes(45);
        
        try {
           fachada.cadastrar(e);
        } catch (SQLException ex) {
            Logger.getLogger(LoaderEventoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ATUALIZAR
        //Evento e2 = fachada.listarPorId(1);
        
        //e2.setNomeResponsavel("José da Silva Nobrega");
        //e2.setNome("Corrida de Jumento");
        
        //fachada.atualizar(e2);
        
        //DELETAR
        //fachada.deletar(e2);
        
        //LISTAR TODOS OS EVENTOS
        for (Evento ev : fachada.listar()) {
            System.out.println(ev);
        }
        
    }
}
