/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.fachada;

import br.edu.ifpb.padroes.entidades.Evento;
import br.edu.ifpb.padroes.persistencia.EventoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Magdiel Ildefonso
 */
public class FachadaEvento {
    public void cadastrar(Evento evento) throws SQLException{
        EventoDAO.persisteEvento(evento);
    }
    
    public void atualizar(Evento evento) throws SQLException{
        EventoDAO.atualizaEvento(evento);
    }
        
    public void deletar(Evento evento) throws SQLException{
        EventoDAO.deletaEvento(evento);
    }
    
    public List<Evento> listar() throws SQLException{
        return EventoDAO.buscarEventos();
    }
}
