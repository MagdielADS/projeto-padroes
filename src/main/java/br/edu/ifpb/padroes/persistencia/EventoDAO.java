/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.persistencia;

import br.edu.ifpb.padroes.entidades.Evento;
import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.entidades.StatusEvento;
import br.edu.ifpb.padroes.persistencia.util.ConexaoLocal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Magdiel Ildefonso
 */
public class EventoDAO {
    private static Connection connection;
    
    public static void persisteEvento(Evento evento) throws SQLException {
        String sql = "insert into evento(nome, descricao, dtInicio, dtFim, nome_responsavel, numero_repeticoes,"
                + "status, id_sala) values(?,?,?,?)";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, evento.getNome());
        pst.setString(2, evento.getDescricao());
        pst.setDate(3, (Date) evento.getDtInicio());
        pst.setDate(4, (Date) evento.getDtFim());
        pst.setString(5, evento.getNomeResponsavel());
        pst.setInt(6, evento.getNumeroRepeticoes());

        //STAUS
        //1 - PEDENTE_LOCAL, 2 - ALOCADO, 3 - REALIZADO, 4 - CANCELADO;
        
        if (evento.getStatus() == StatusEvento.PEDENTE_LOCAL) {
            pst.setInt(7, 1);
        } else if (evento.getStatus() == StatusEvento.ALOCADO) {
            pst.setInt(7, 2);
        } else if (evento.getStatus() == StatusEvento.REALIZADO) {
            pst.setInt(7, 3);
        } else if (evento.getStatus() == StatusEvento.CANCELADO) {
            pst.setInt(7, 4);
        }

        pst.setString(8, evento.getSala().getIdenticacao());

        pst.execute();
        connection.close();
        pst.close();
    }

    public static void atualizaEvento(Evento evento) throws SQLException {
        String sql = "update evento set nome = ?, descricao = ?, dtInicio = ?, dtFim = ?, "
                + "nome_responsavel = ?, numero_repeticoes = ?"
                + " status = ?, id_sala = ? where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setString(1, evento.getNome());
        pst.setString(2, evento.getDescricao());
        pst.setDate(3, (Date) evento.getDtInicio());
        pst.setDate(4, (Date) evento.getDtFim());
        pst.setString(5, evento.getNomeResponsavel());
        pst.setInt(6, evento.getNumeroRepeticoes());

        //STAUS
        //1 - PEDENTE_LOCAL, 2 - ALOCADO, 3 - REALIZADO, 4 - CANCELADO;
        
        if (evento.getStatus() == StatusEvento.PEDENTE_LOCAL) {
            pst.setInt(7, 1);
        } else if (evento.getStatus() == StatusEvento.ALOCADO) {
            pst.setInt(7, 2);
        } else if (evento.getStatus() == StatusEvento.REALIZADO) {
            pst.setInt(7, 3);
        } else if (evento.getStatus() == StatusEvento.CANCELADO) {
            pst.setInt(7, 4);
        }

        pst.setString(8, evento.getSala().getIdenticacao());
        pst.setInt(9, evento.getId());

        pst.execute();
        connection.close();
        pst.close();
    }

    public static void deletaEvento(Evento evento) throws SQLException {
        String sql = "delete from evento where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setInt(1, evento.getId());

        pst.execute();
        connection.close();
        pst.close();
    }
    
    public static Evento buscarEventoPorId(int id) throws SQLException {
        Evento eventoResult = null;
        String sql = "select * from evento where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            Evento evento = new Evento();
            StatusEvento status = null;
            //STAUS
            //1 - PEDENTE_LOCAL, 2 - ALOCADO, 3 - REALIZADO, 4 - CANCELADO;

            if (rs.getInt("status") == 1) {
                status = StatusEvento.PEDENTE_LOCAL;
            } else if (rs.getInt("status") == 2) {
                status = StatusEvento.ALOCADO;
            } else if (rs.getInt("status") == 3) {
                status = StatusEvento.REALIZADO;
            } else if (rs.getInt("status") == 4) {
                status = StatusEvento.CANCELADO;
            }
            
            evento.setDescricao(rs.getString("descricao"));
            evento.setDtFim(rs.getDate("dtFim"));
            evento.setDtInicio(rs.getDate("dtInicio"));
            evento.setId(rs.getInt("id"));
            evento.setNome(rs.getString("nome"));
            evento.setNomeResponsavel(rs.getString("nome_responsavel"));
            evento.setNumeroRepeticoes(rs.getInt("nomero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            
            eventoResult = evento;
        }

        pst.execute();
        connection.close();
        pst.close();
        
        return eventoResult;
    }
    
    public static List<Evento> buscarEventos() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "select * from evento";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            Evento evento = new Evento();
            StatusEvento status = null;
            //STAUS
            //1 - PEDENTE_LOCAL, 2 - ALOCADO, 3 - REALIZADO, 4 - CANCELADO;

            if (rs.getInt("status") == 1) {
                status = StatusEvento.PEDENTE_LOCAL;
            } else if (rs.getInt("status") == 2) {
                status = StatusEvento.ALOCADO;
            } else if (rs.getInt("status") == 3) {
                status = StatusEvento.REALIZADO;
            } else if (rs.getInt("status") == 4) {
                status = StatusEvento.CANCELADO;
            }
            
            evento.setDescricao(rs.getString("descricao"));
            evento.setDtFim(rs.getDate("dtFim"));
            evento.setDtInicio(rs.getDate("dtInicio"));
            evento.setId(rs.getInt("id"));
            evento.setNome(rs.getString("nome"));
            evento.setNomeResponsavel(rs.getString("nome_responsavel"));
            evento.setNumeroRepeticoes(rs.getInt("nomero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            
            eventos.add(evento);
        }

        pst.execute();
        connection.close();
        pst.close();
        
        return eventos;
    }
    
    public static void alocarSala(Sala sala, Evento evento) throws SQLException{
        String sql = "update evento set id_sala = ? where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setString(1, evento.getSala().getIdenticacao());
        pst.setInt(2, evento.getId());

        pst.execute();
        connection.close();
        pst.close();
    }
    
    public static boolean verificaAlocacaoSala(Sala sala, Evento evento) throws SQLException {
        boolean result = false;
        String sql = "select * from evento where id_sala = ? and dtInicial = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, sala.getIdenticacao());
        pst.setDate(2, (Date) evento.getDtInicio());
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            result = true;
        }
        return result;
    }
    
    public static void alteraStatus(StatusEvento status, Evento evento) throws SQLException{
        String sql = "update evento set status = ? where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        
        //STAUS
        //1 - PEDENTE_LOCAL, 2 - ALOCADO, 3 - REALIZADO, 4 - CANCELADO;
        if (status == StatusEvento.PEDENTE_LOCAL) {
            pst.setInt(1, 1);
        } else if (status == StatusEvento.ALOCADO) {
            pst.setInt(1, 2);
        } else if (status == StatusEvento.REALIZADO) {
            pst.setInt(1, 3);
        } else if (status == StatusEvento.CANCELADO) {
            pst.setInt(1, 4);
        }
        pst.setInt(2, evento.getId());

        pst.execute();
        connection.close();
        pst.close();
    }
    
    public static List<Evento> buscaEventosPorSala(Sala sala) throws SQLException{
        List<Evento> eventos = new ArrayList<>();
        String sql = "select * from evento where id_sala = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, sala.getIdenticacao());
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Evento evento = new Evento();
            StatusEvento status = null;
            //STAUS
            //1 - PEDENTE_LOCAL, 2 - ALOCADO, 3 - REALIZADO, 4 - CANCELADO;

            if (rs.getInt("status") == 1) {
                status = StatusEvento.PEDENTE_LOCAL;
            } else if (rs.getInt("status") == 2) {
                status = StatusEvento.ALOCADO;
            } else if (rs.getInt("status") == 3) {
                status = StatusEvento.REALIZADO;
            } else if (rs.getInt("status") == 4) {
                status = StatusEvento.CANCELADO;
            }

            evento.setDescricao(rs.getString("descricao"));
            evento.setDtFim(rs.getDate("dtFim"));
            evento.setDtInicio(rs.getDate("dtInicio"));
            evento.setId(rs.getInt("id"));
            evento.setNome(rs.getString("nome"));
            evento.setNomeResponsavel(rs.getString("nome_responsavel"));
            evento.setNumeroRepeticoes(rs.getInt("nomero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);

            eventos.add(evento);
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventos;
    }
}

