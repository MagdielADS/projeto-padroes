/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.fachada;

import br.edu.ifpb.padroes.entidades.Evento;
import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.entidades.StatusEvento;
import br.edu.ifpb.padroes.persistencia.EventoDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Magdiel Ildefonso
 */
/**
 * A classe FachadaEvento é a parte do padrão Facade, criando uma abstração
 * entre a camada de aplicação e a camada de persistência
 *
 * @author Magdiel Ildefonso
 */
public class FachadaEvento {

    /**
     * Cadastra o evento
     *
     * @param evento abstração para o evento que será cadastrado
     * @throws SQLException
     */
    public void cadastrar(Evento evento) throws SQLException {
        EventoDAO.persisteEvento(evento);
    }

    /**
     * Atualiza o evento informado
     *
     * @throws SQLException
     * @param evento
     */
    public void atualizar(Evento evento) throws SQLException {
        EventoDAO.atualizaEvento(evento);
    }

    /**
     * Deleta o evento informado
     *
     * @throws SQLException
     * @param evento
     */
    public void deletar(Evento evento) throws SQLException {
        EventoDAO.deletaEvento(evento);
    }

    /**
     * Lista todos os eventos que não estão com status Cancelado
     *
     * @return List como os eventos cadastrado que não estão com o status
     * cancelado
     * @throws SQLException
     */
    public List<Evento> listar() throws SQLException {
        return EventoDAO.buscarEventos();
    }

    /**
     * Lista O evento com o id informado
     *
     * @param id o id do evento que será listado
     * @return Evento se houver evento cadastrado com o id informado
     * @throws SQLException será lançado se houver erro no banco
     */
    public Evento listarPorId(int id) throws SQLException {
        return EventoDAO.buscarEventoPorId(id);
    }

    /**
     * Aloca uma sala a um evento
     *
     * @param sala sala que deve ser alocado ao evento
     * @param evento evento a qual a sala será alocada
     * @throws SQLException será lançado se houver erro no banco
     */
    public void alocarSalaEvento(Sala sala, Evento evento) throws SQLException {
        EventoDAO.alocarSala(sala, evento);
        EventoDAO.alteraStatus(StatusEvento.ALOCADO, evento);
    }

    /**
     * Desaloca a sala do evento
     *
     * @param evento evento no qual será retirado a alocação da sala
     * @throws SQLException será lançado se houver erro no banco
     */
    public void desalocarEvento(Evento evento) throws SQLException {
        EventoDAO.desalocarSala(evento);
        EventoDAO.alteraStatus(StatusEvento.PEDENTE_LOCAL, evento);
    }

    /**
     * Troca o status do evento para cancelado, além de desalocar a sala se
     * existir
     *
     * @param evento evento que será cancelado
     * @throws SQLException será lançado se houver erro no banco
     */
    public void cancelarEvento(Evento evento) throws SQLException {
        EventoDAO.desalocarSala(evento);
        EventoDAO.alteraStatus(StatusEvento.CANCELADO, evento);
    }

    /**
     * Confirma o evento setando o status de acordo com o parâmetro que pode ser
     * Realizado ou Cancelado
     *
     * @param status indica o status que o evento irá receber após ser
     * confirmado
     * @param evento evento a ser confirmado a partir do status informado
     * @throws SQLException será lançado se houver erro no banco
     */
    public void confirmarEvento(StatusEvento status, Evento evento) throws SQLException {
        EventoDAO.desalocarSala(evento);
        if (status == StatusEvento.REALIZADO) {
            EventoDAO.alteraStatus(StatusEvento.REALIZADO, evento);
        } else {
            EventoDAO.alteraStatus(StatusEvento.CANCELADO, evento);
        }
    }
}
