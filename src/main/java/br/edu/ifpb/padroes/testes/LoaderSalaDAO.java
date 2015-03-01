/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.testes;

import br.edu.ifpb.padroes.entidades.Sala;
import br.edu.ifpb.padroes.entidades.TipoSala;
import br.edu.ifpb.padroes.fachada.FachadaSala;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magdiel Ildefonso
 */
public class LoaderSalaDAO {
    public static void main(String[] args) {
        FachadaSala fachada = new FachadaSala();
        try {
            //CADASTRO - OK
            Sala sala = new Sala();
            //sala.setApelido("Sala da tv");
            sala.setCapacidade(40);
            sala.setIdenticacao("Bloco de Informatica", "4");
            sala.setTipoSala(TipoSala.AULA_NORMAL);
            
            fachada.cadastrar(sala);
            //BUSCA POR ID - OK
            Sala sala2 = fachada.listarPorID("CdI2");
            //ATUALIZAR
            //sala2.setApelido("Sala de aula");
            //sala2.setCapacidade(10);
            //sala2.setTipoSala(TipoSala.VIDEO_CONFERENCIA);
            
            //fachada.atualizar(sala2);
            
            //DELETAR
            //fachada.deletar(sala2);
            
            //LISTAR TODAS
            for (Sala s : fachada.listar()) {
                System.out.println(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaderSalaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
