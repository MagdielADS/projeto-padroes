/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.fachada;

import br.edu.ifpb.padroes.entidades.Evento;
import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.persistencia.SalaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Magdiel Ildefonso 
 */
/**
 * * A classe FachadaSala é a parte do padrão Facade, criando uma abstração
 * entre a camada de aplicação e a camada de persistência
 *
 * @author Magdiel Ildefonso
 */
public class FachadaSala {

    /**
     * Cadastra a sala
     *
     * @param sala abstração para a sala que será cadastrado
     * @throws java.sql.SQLException
     */
    public void cadastrar(Sala sala) throws SQLException {
        SalaDAO.persisteSala(sala);
    }

    /**
     * Atualiza a sala informada
     *
     * @param sala
     * @throws java.sql.SQLException
     */
    public void atualizar(Sala sala) throws SQLException {
        SalaDAO.atualizaSala(sala);
    }

    /**
     * Deleta a sala
     *
     * @param sala
     * @throws java.sql.SQLException
     */
    public void deletar(Sala sala) throws SQLException {
        SalaDAO.deletaSala(sala);
    }

    /**
     * Retorna a lista com todas as salas cadastradas
     *
     * @return Lista com todas as salas que estiverem cadastradas no banco
     * @throws java.sql.SQLException
     */
    public List<Sala> listar() throws SQLException {
        return SalaDAO.buscarSalas();
    }

    /**
     * Retorna a sala com o id informado
     *
     * @param id
     * @return Sala com o id informado
     * @throws java.sql.SQLException
     */
    public Sala listarPorID(String id) throws SQLException {
        return SalaDAO.buscarSalaPorIdentificacao(id);
    }
    
    /**
     * Lista as salas qu satisfazem os requisitos do evento, não estejam alocadas a outro evento na mesma data 
     * e possuam capacidade igual ou maior do que a quantidade de participantes do evento
     * @param evento
     * @return Lista de salas que satisfazem aos requisitos do evento
     * @throws SQLException 
     */
    public List<Sala> listarSalasQueSatisfazem(Evento evento) throws SQLException{
        return SalaDAO.buscarSalasQueSatisfacamEvento(evento);
    }
}
