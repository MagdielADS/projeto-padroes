/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.persistencia;

import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.entidades.SalaBuilder;
import br.edu.ifpb.padroes.entidades.TipoSala;
import br.edu.ifpb.padroes.persistencia.util.ConexaoLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Magdiel Ildefonso
 */
public class SalaDAO {

    private static Connection connection;

    public static void persisteSala(Sala sala) throws SQLException {
        String sql = "insert into sala(identificacao, capacidade, apelido, tipo) values(?,?,?,?)";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, sala.getIdenticacao());
        pst.setInt(2, sala.getCapacidade());
        
        //TIPO
        //1 - AULA NORMAL
        //2 - AULA INTELIGENTE
        //3 - LABORATORIO
        //4 - CONFERENCIA
        //5 - VIDEO CONFERENCIA
        
        if(sala.getTipoSala() == TipoSala.AULA_NORMAL){
            pst.setInt(3, 1);
        }else if(sala.getTipoSala() == TipoSala.AULA_INTELIGENTE){
            pst.setInt(3, 2);
        }else if(sala.getTipoSala() == TipoSala.LABORATORIO){
            pst.setInt(3, 3);
        }else if(sala.getTipoSala() == TipoSala.CONFERENCIA){
            pst.setInt(3, 4);
        }else if(sala.getTipoSala() == TipoSala.VIDEO_CONFERENCIA){
            pst.setInt(3, 5);
        }
        
        pst.setObject(4, sala.getTipoSala());

        pst.execute();
        connection.close();
        pst.close();
    }

    public static void atualizaSala(Sala sala) throws SQLException {
        String sql = "update sala set capacidade = ?, apelido = ?, tipo = ?"
                + " where identificacao ilike ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setInt(1, sala.getCapacidade());
        pst.setString(2, sala.getApelido());
        
        //TIPO
        //1 - AULA NORMAL
        //2 - AULA INTELIGENTE
        //3 - LABORATORIO
        //4 - CONFERENCIA
        //5 - VIDEO CONFERENCIA
        
        if(sala.getTipoSala() == TipoSala.AULA_NORMAL){
            pst.setInt(3, 1);
        }else if(sala.getTipoSala() == TipoSala.AULA_INTELIGENTE){
            pst.setInt(3, 2);
        }else if(sala.getTipoSala() == TipoSala.LABORATORIO){
            pst.setInt(3, 3);
        }else if(sala.getTipoSala() == TipoSala.CONFERENCIA){
            pst.setInt(3, 4);
        }else if(sala.getTipoSala() == TipoSala.VIDEO_CONFERENCIA){
            pst.setInt(3, 5);
        }
        
        pst.setString(4, sala.getIdenticacao());

        pst.execute();
        connection.close();
        pst.close();
    }

    public static void deletaSala(Sala sala) throws SQLException {
        String sql = "delete from sala where identificacao ilike ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);

        pst.setString(1, sala.getIdenticacao());

        pst.execute();
        connection.close();
        pst.close();
    }
    
    public static Sala buscarSalaPorIdentificacao(String identificacao) throws SQLException {
        Sala salaResult = null;
        String sql = "select * from sala where identificacao ilike ?";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        pst.setString(1, identificacao);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            TipoSala tipo = null;
            //TIPO
            //1 - AULA NORMAL
            //2 - AULA INTELIGENTE
            //3 - LABORATORIO
            //4 - CONFERENCIA
            //5 - VIDEO CONFERENCIA

            if (rs.getInt("tipo") == 1) {
                tipo = TipoSala.AULA_NORMAL;
            } else if (rs.getInt("tipo") == 2) {
                tipo = TipoSala.AULA_INTELIGENTE;
            } else if (rs.getInt("tipo") == 3) {
                tipo = TipoSala.LABORATORIO;
            } else if (rs.getInt("tipo") == 4) {
                tipo = TipoSala.CONFERENCIA;
            } else if (rs.getInt("tipo") == 5) {
                tipo = TipoSala.VIDEO_CONFERENCIA;
            }
            
            SalaBuilder sBuilder = new SalaBuilder(rs.getString("identificacao"), rs.getInt("capacidade"), tipo);
            
            if(rs.getString("apelido") != null || rs.getString("apelido") != ""){
                Sala sala = new SalaBuilder(rs.getString("identificacao"), rs.getInt("capacidade"), tipo)
                        .comApelido(rs.getString("apelido")).construa();
                salaResult = sala;
            }else{
                Sala sala = new SalaBuilder(rs.getString("identificacao"), rs.getInt("capacidade"), tipo).construa();
                salaResult = sala;
            }
        }

        pst.execute();
        connection.close();
        pst.close();
        
        return salaResult;
    }
    
    public static List<Sala> buscarSalas() throws SQLException {
        List<Sala> salas = new ArrayList<>();
        String sql = "select * from sala";

        connection = ConexaoLocal.getInstance().createConnection();
        PreparedStatement pst;

        pst = connection.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        
        while (rs.next()) {
            Sala sala = null;
            TipoSala tipo = null;
            //TIPO
            //1 - AULA NORMAL
            //2 - AULA INTELIGENTE
            //3 - LABORATORIO
            //4 - CONFERENCIA
            //5 - VIDEO CONFERENCIA

            if (rs.getInt("tipo") == 1) {
                tipo = TipoSala.AULA_NORMAL;
            } else if (rs.getInt("tipo") == 2) {
                tipo = TipoSala.AULA_INTELIGENTE;
            } else if (rs.getInt("tipo") == 3) {
                tipo = TipoSala.LABORATORIO;
            } else if (rs.getInt("tipo") == 4) {
                tipo = TipoSala.CONFERENCIA;
            } else if (rs.getInt("tipo") == 5) {
                tipo = TipoSala.VIDEO_CONFERENCIA;
            }
            
            SalaBuilder sBuilder = new SalaBuilder(rs.getString("identificacao"), rs.getInt("capacidade"), tipo);
            
            if(rs.getString("apelido") != null || rs.getString("apelido") != ""){
                sala = new SalaBuilder(rs.getString("identificacao"), rs.getInt("capacidade"), tipo)
                        .comApelido(rs.getString("apelido")).construa();
            }else{
                sala = new SalaBuilder(rs.getString("identificacao"), rs.getInt("capacidade"), tipo).construa();
            }
            
            salas.add(sala);
        }

        pst.execute();
        connection.close();
        pst.close();
        
        return salas;
    }
}
