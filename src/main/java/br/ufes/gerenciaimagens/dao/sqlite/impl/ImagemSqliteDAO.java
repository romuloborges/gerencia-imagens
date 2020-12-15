package br.ufes.gerenciaimagens.dao.sqlite.impl;

import br.ufes.gerenciaimagens.dao.interfaces.IImagemDAO;
import br.ufes.gerenciaimagens.dao.manager.SqliteManager;
import br.ufes.gerenciaimagens.model.Imagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rborges
 */
public class ImagemSqliteDAO implements IImagemDAO {
    
    private SqliteManager manager;
    
    public ImagemSqliteDAO() {
        this.manager = new SqliteManager();
    }

    @Override
    public List<Imagem> obterTodasNaoExcluidas() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String sql = "SELECT id, caminho FROM Imagem WHERE excluida = 0;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            List<Imagem> imagens = new ArrayList<>();
            
            while(rs.next()) {
                Imagem imagem = new Imagem();
                
                imagem.setId(rs.getLong(1));
                imagem.setCaminho(rs.getString(2));
                imagem.setExcluida(false);
                
                imagens.add(imagem);
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return imagens;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public void excluir(Long id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            String sql = "UPDATE Imagem SET excluida = 1 WHERE id = ?;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }
    
}
