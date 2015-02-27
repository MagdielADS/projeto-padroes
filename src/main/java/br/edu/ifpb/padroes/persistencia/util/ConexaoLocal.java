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
 */
public class ConexaoLocal {

    private static ConexaoLocal instancia;
    private final File file;
    private final Properties prop;

    private ConexaoLocal() {
        file = new File("conexaolocal.properties");
        //file = new File("conexaoremota.properties");
        prop = new Properties();
    }

    public static ConexaoLocal getInstance() {
        if (instancia == null) {
            return instancia = new ConexaoLocal();
        }
        return instancia;
    }

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
