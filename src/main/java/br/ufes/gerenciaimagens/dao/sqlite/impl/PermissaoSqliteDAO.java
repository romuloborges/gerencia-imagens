package br.ufes.gerenciaimagens.dao.sqlite.impl;

import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;
import br.ufes.gerenciaimagens.dao.manager.SqliteManager;
import br.ufes.gerenciaimagens.model.TipoPermissao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author rborges
 */
public class PermissaoSqliteDAO implements IPermissaoDAO {
    
    private SqliteManager manager;
    
    public PermissaoSqliteDAO() {
        this.manager = new SqliteManager();
    }

    @Override
    public boolean possuiPermissaoDeVisualizar(Long idUsuario, Long idImagem) throws Exception {
        try {
            String SQL = "SELECT * FROM Permissao WHERE id_usuario = ?, id_imagem = ?, tipo = ?;";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
            ps.setLong(2, idImagem);
            ps.setString(3, TipoPermissao.VISUALIZACAO.name());
            
            ResultSet rs = ps.executeQuery();
            boolean possuiPermissaoDeVisualizacao = rs.next();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return possuiPermissaoDeVisualizacao;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public boolean possuiPermissaoDeExcluir(Long idUsuario, Long idImagem) throws Exception {
        try {
            String SQL = "SELECT * FROM Permissao WHERE id_usuario = ?, id_imagem = ?, tipo = ?;";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
            ps.setLong(2, idImagem);
            ps.setString(3, TipoPermissao.EXCLUSAO.name());
            
            ResultSet rs = ps.executeQuery();
            boolean possuiPermissaoDeVisualizacao = rs.next();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return possuiPermissaoDeVisualizacao;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public boolean possuiPermissaoDeCompartilhar(Long idUsuario, Long idImagem) throws Exception {
        try {
            String SQL = "SELECT * FROM Permissao WHERE id_usuario = ?, id_imagem = ?, tipo = ?;";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuario);
            ps.setLong(2, idImagem);
            ps.setString(3, TipoPermissao.COMPARTILHAMENTO.name());
            
            ResultSet rs = ps.executeQuery();
            boolean possuiPermissaoDeVisualizacao = rs.next();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return possuiPermissaoDeVisualizacao;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }
    
}