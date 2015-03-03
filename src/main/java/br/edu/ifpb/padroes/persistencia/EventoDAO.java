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
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Magdiel Ildefonso
 *
 */
/**
 * Classe de persistência para Eventos, responsável por manter o evento,
 * persistindo, atualizando, excluindo e listando de diversas formas os eventos
 *
 * @author Magdiel Ildefonso
 */
public class EventoDAO {

    private static Connection connection;

    /**
     * Persiste o evento
     *
     * @param evento evento que será persistido
     * @throws java.sql.SQLException
     */
    public static void persisteEvento(Evento evento) throws SQLException {
        String sql = "insert into evento(nome, descricao, dtInicio, dtFim, nome_responsavel, numero_repeticoes,"
                + "status, qtde_participantes) values(?,?,?,?,?,?,?,?)";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, evento.getNome());
        pst.setString(2, evento.getDescricao());
        pst.setDate(3, new java.sql.Date(evento.getDtInicio().getTime()));
        pst.setDate(4, new java.sql.Date(evento.getDtFim().getTime()));
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

        pst.setInt(8, evento.getQtde_participantes());

        pst.execute();
        connection.close();
        pst.close();
    }

    /**
     * Atualiza o evento
     *
     * @param evento
     * @throws java.sql.SQLException
     */
    public static void atualizaEvento(Evento evento) throws SQLException {
        String sql = "update evento set nome = ?, descricao = ?, dtInicio = ?, dtFim = ?, "
                + "nome_responsavel = ?, numero_repeticoes = ?,"
                + " status = ?, id_sala = ?, qtde_participantes where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setString(1, evento.getNome());
        pst.setString(2, evento.getDescricao());
        pst.setDate(3, new java.sql.Date(evento.getDtInicio().getTime()));
        pst.setDate(4, new java.sql.Date(evento.getDtFim().getTime()));
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
        pst.setInt(9, evento.getQtde_participantes());
        pst.setInt(10, evento.getId());

        pst.execute();
        connection.close();
        pst.close();
    }

    /**
     * Deleta o evento
     *
     * @param evento
     * @throws java.sql.SQLException
     */
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

    /**
     *Busca eventos por id
     * @param id
     * @return 
     * @throws java.sql.SQLException 
     */
    public static Evento buscarEventoPorId(int id) throws SQLException {
        Evento eventoResult = null;
        String sql = "select * from evento where id = ? and status <> 4";

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
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            evento.setQtde_participantes(rs.getInt("qtde_participantes"));

            eventoResult = evento;
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventoResult;
    }

    /**
     * Listas todos os eventos que não foram cancelados
     *
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Evento> buscarEventos() throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "select * from evento where status <> 4";

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
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            evento.setId(rs.getInt("qtde_participantes"));

            eventos.add(evento);
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventos;
    }

    /**
     * Atualiza a sala do evento
     *
     * @param sala
     * @param evento
     * @throws java.sql.SQLException
     */
    public static void alocarSala(Sala sala, Evento evento) throws SQLException {
        String sql = "update evento set id_sala = ? where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setString(1, sala.getIdenticacao());
        pst.setInt(2, evento.getId());

        pst.execute();
        connection.close();
        pst.close();
    }

    /**
     * Edita a sala do evento para null
     *
     * @param evento
     * @throws java.sql.SQLException
     */
    public static void desalocarSala(Evento evento) throws SQLException {
        String sql = "update evento set id_sala = ? where id = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setString(1, null);
        pst.setInt(2, evento.getId());

        pst.execute();
        connection.close();
        pst.close();
    }

    /**
     * Verifica se a sala informado está alocada para o outro evento na mesma
     * data do evento no qual se deseja alocar
     *
     * @param sala
     * @param evento
     * @return
     * @throws java.sql.SQLException
     */
    public static boolean verificaAlocacaoSala(Sala sala, Evento evento) throws SQLException {
        boolean result = false;
        String sql = "select * from evento where id_sala = ? and dtInicial = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, sala.getIdenticacao());
        pst.setDate(2, new java.sql.Date(evento.getDtInicio().getTime()));
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            result = true;
        }
        return result;
    }

    /**
     * Altera o status do evento
     *
     * @param status
     * @param evento
     * @throws java.sql.SQLException
     */
    public static void alteraStatus(StatusEvento status, Evento evento) throws SQLException {
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

    /**
     * Busca eventos por sala
     *
     * @param sala
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Evento> buscaEventosPorSala(Sala sala) throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "select * from evento where id_sala ilike ? and status <> 4";

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
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            evento.setId(rs.getInt("qtde_participantes"));

            eventos.add(evento);
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventos;
    }

    /**
     * Busca eventos por status Lista todos os eventos com o status informado
     *
     * @param status
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Evento> buscaEventosPorStatus(StatusEvento status) throws SQLException {
        List<Evento> eventos = new ArrayList<>();
        String sql = "select * from evento where status = ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        if (status == StatusEvento.PEDENTE_LOCAL) {
            pst.setInt(1, 1);
        } else if (status == StatusEvento.ALOCADO) {
            pst.setInt(1, 2);
        } else if (status == StatusEvento.REALIZADO) {
            pst.setInt(1, 3);
        } else if (status == StatusEvento.CANCELADO) {
            pst.setInt(1, 4);
        }
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Evento evento = new Evento();
            StatusEvento st = null;
            //STAUS
            //1 - PEDENTE_LOCAL, 2 - ALOCADO, 3 - REALIZADO, 4 - CANCELADO;

            if (rs.getInt("status") == 1) {
                st = StatusEvento.PEDENTE_LOCAL;
            } else if (rs.getInt("status") == 2) {
                st = StatusEvento.ALOCADO;
            } else if (rs.getInt("status") == 3) {
                st = StatusEvento.REALIZADO;
            } else if (rs.getInt("status") == 4) {
                st = StatusEvento.CANCELADO;
            }

            evento.setDescricao(rs.getString("descricao"));
            evento.setDtFim(rs.getDate("dtFim"));
            evento.setDtInicio(rs.getDate("dtInicio"));
            evento.setId(rs.getInt("id"));
            evento.setNome(rs.getString("nome"));
            evento.setNomeResponsavel(rs.getString("nome_responsavel"));
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(st);
            evento.setId(rs.getInt("qtde_participantes"));

            eventos.add(evento);
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventos;
    }

    /**
     * Busca o evento pelo nome
     *
     * @param nome
     * @return
     * @throws java.sql.SQLException
     */
    public static Evento buscarEventoPorNome(String nome) throws SQLException {
        Evento eventoResult = null;
        String sql = "select * from evento where nome ilike ? and status <> 4";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, nome);
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
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            evento.setId(rs.getInt("qtde_participantes"));
            eventoResult = evento;
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventoResult;
    }

    /**
     * Busca o evento pela descrição do mesmo
     *
     * @param descricao
     * @return
     * @throws java.sql.SQLException
     */
    public static Evento buscarEventoPorDescricao(String descricao) throws SQLException {
        Evento eventoResult = null;
        String sql = "select * from evento where descricao ilike ? and status <> 4";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, descricao);
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
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            evento.setId(rs.getInt("qtde_participantes"));

            eventoResult = evento;
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventoResult;
    }

    /**
     * Busca o evento pela data de realização
     *
     * @param data
     * @return
     * @throws java.sql.SQLException
     */
    public static Evento buscarEventoPorData(Date data) throws SQLException {
        Evento eventoResult = null;
        String sql = "select * from evento where dtInicio = ? and status <> 4";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setDate(1, new java.sql.Date(data.getTime()));
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
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            evento.setId(rs.getInt("qtde_participantes"));

            eventoResult = evento;
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventoResult;
    }

    /**
     * Busca o evento pelo nome do usuário responsável
     *
     * @param nomeUsuario
     * @return
     * @throws java.sql.SQLException
     */
    public static Evento buscarEventoPorUsuario(String nomeUsuario) throws SQLException {
        Evento eventoResult = null;
        String sql = "select * from evento where nome_responsavel ilike ? and status <> 4";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, nomeUsuario);
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
            evento.setNumeroRepeticoes(rs.getInt("numero_repeticoes"));
            evento.setSala(SalaDAO.buscarSalaPorIdentificacao(rs.getString("id_sala")));
            evento.setStatus(status);
            evento.setId(rs.getInt("qtde_participantes"));

            eventoResult = evento;
        }

        pst.execute();
        connection.close();
        pst.close();

        return eventoResult;
    }
}
