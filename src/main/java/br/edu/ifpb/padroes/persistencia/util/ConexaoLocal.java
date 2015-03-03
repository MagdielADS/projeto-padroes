package br.edu.ifpb.padroes.persistencia.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Magdiel Ildefonso
 *
 */
/**
 * A classe de ConexaoLocal é um singleton de conexão com o banco, a mesma
 * possibilita assim um única instânica de acesso ao banco
 *
 * @author Magdiel Ildefonso
 */
public class ConexaoLocal {

    private static ConexaoLocal instancia;
    private final File file;
    private final Properties prop;

    private ConexaoLocal() {
        /**
         * conexaolocal.properties é o arquivo com os parâmetros de conexão ao
         * banco
         */
        file = new File("conexaolocal.properties");
        prop = new Properties();
    }

    /**
     * Retorna a instância de ConexaoLocal
     *
     * @return ConexaoLocal instância única dessa classe
     */
    public static ConexaoLocal getInstance() {
        if (instancia == null) {
            return instancia = new ConexaoLocal();
        }
        return instancia;
    }

    /**
     * Cria a conexão com o banco a partir do arquivo de propriedades
     *
     * @return Conexão com o banco de dados, realizada a partir de um arquivo de propriedades
     */
    public Connection createConnection() {
        try {
            prop.load(new FileInputStream(file));
            Class.forName(prop.getProperty("driver"));
            return DriverManager.getConnection(prop.getProperty("path"), prop.getProperty("login"), prop.getProperty("senha"));
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            Logger.getLogger(ConexaoLocal.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
