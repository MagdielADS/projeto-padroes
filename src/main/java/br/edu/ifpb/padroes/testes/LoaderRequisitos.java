/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.testes;

import br.edu.ifpb.padroes.entidades.Evento;
import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.entidades.StatusEvento;
import br.edu.ifpb.padroes.fachada.FachadaEvento;
import br.edu.ifpb.padroes.fachada.FachadaSala;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magdiel Ildefonso
 */
public class LoaderRequisitos {
    public static void main(String[] args) {
        FachadaEvento fe = new FachadaEvento();
        FachadaSala fs = new FachadaSala();
        
        try {
            Evento e = fe.listarPorId(1);
            Sala s = fs.listarPorID("BI4");
            
            //fe.alocarSalaEvento(s, e);
            //fe.desalocarEvento(e);
            //fe.cancelarEvento(e);
            //fe.confirmarEvento(StatusEvento.REALIZADO, e);
            
            //BUSCAR EVENTOS POR FILTROS
            System.out.println("STATUS");
            for (Evento evento : fe.buscarEventosPorStatus(StatusEvento.PEDENTE_LOCAL)) {
                System.out.println(evento);
            }
            
            //BUSCA EVENTO POR NOME
            System.out.println("NOME DO EVENTO");
            System.out.println(fe.buscarEventoPorNome("Olimpíadas de programação"));
            
            //BUSCA EVENTO POR DESCRICAO
            System.out.println("DESCRIÇÃO");
            System.out.println(fe.buscarEventoPorDescricao("Primeira aula do período, apresentação do curso aos feras"));
            
            //BUSCA EVENTO POR DATA
            Calendar c = Calendar.getInstance();
            c.set(2015, 2, 3); 
            System.out.println("DATA");
            System.out.println(fe.buscarEventoPorData(c.getTime()));
            
            //BUSCA EVENTO POR USUARIO
            System.out.println("RESPONSAVEL");
            System.out.println(fe.buscarEventoPorResponsavel("Diogo"));
            
        } catch (SQLException ex) {
            Logger.getLogger(LoaderRequisitos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
}
