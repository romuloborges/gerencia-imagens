package br.ufes.gerenciaimagens.dao.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rborges
 */
public class SqliteManager {
    
    private Connection conn = null;
    
    public Connection conectar() {
        try {
            if (conn == null || conn.isClosed()) {
                String url = "jdbc:sqlite:src/main/java/br/ufes/gerenciaimagens/dao/db/gerencia-imagens.db";
                conn = DriverManager.getConnection(url);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }
    
    public void close() {
        close(this.conn);
    }
    
    public void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void close(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            
            if (ps != null) {
                ps.close();
            }
            
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void abreTransacao() throws SQLException {
        if (this.conn == null) {
            throw new RuntimeException("A conexão com o banco não está ativa");
        }
        
        PreparedStatement ps = conn.prepareStatement("BEGIN TRANSACTION;");
        ps.execute();
    }
    
    public void fechaTransacao() throws SQLException {
        if (this.conn == null) {
            throw new RuntimeException("A conexão com o banco não está ativa");
        }
        
        PreparedStatement ps = conn.prepareStatement("COMMIT;");
        ps.execute();
    }
    
    public void desfazTransacao() throws SQLException {
        if (this.conn == null) {
            throw new RuntimeException("A conexão com o banco não está ativa");
        }
        
        PreparedStatement ps = conn.prepareStatement("ROLLBACK;");
        ps.execute();
    }
    
}
