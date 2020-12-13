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
        try {
            String SQL = "SELECT id, caminho FROM Imagem WHERE excluida = 0;";

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            
            List<Imagem> imagens = new ArrayList<>();
            
            while(rs.next()) {
                Imagem imagem = new Imagem();
                
                imagem.setId(rs.getLong(1));
                imagem.setCaminho(rs.getString(2));
                imagem.setExcluida(false);
                
                imagens.add(imagem);
            }

            this.manager.fechaTransacao();
            this.manager.close();
            
            return imagens;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }
    
}
