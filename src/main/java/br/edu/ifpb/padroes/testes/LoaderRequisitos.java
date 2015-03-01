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
import br.edu.ifpb.padroes.persistencia.SalaDAO;
import java.sql.SQLException;
import java.util.List;
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
            fe.confirmarEvento(StatusEvento.REALIZADO, e);
        } catch (SQLException ex) {
            Logger.getLogger(LoaderRequisitos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
}
