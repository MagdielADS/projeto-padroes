/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.fachada;

import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.persistencia.SalaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Magdiel Ildefonso
 */
public class FachadaSala {
    public void cadastrar(Sala sala) throws SQLException{
        SalaDAO.persisteSala(sala);
    }
    
    public void atualizar(Sala sala) throws SQLException{
        SalaDAO.atualizaSala(sala);
    }
    
    public void deletar(Sala sala) throws SQLException{
        SalaDAO.deletaSala(sala);
    }
    
    public List<Sala> listar() throws SQLException{
        return SalaDAO.buscarSalas();
    }
    
    public Sala listarPorID(String id) throws SQLException{
        return SalaDAO.buscarSalaPorIdentificacao(id);
    }
}
