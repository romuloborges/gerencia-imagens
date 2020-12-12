package br.ufes.gerenciaimagens.repository;

import br.ufes.gerenciaimagens.dao.interfaces.IImagemDAO;
import br.ufes.gerenciaimagens.dao.sqlite.impl.ImagemSqliteDAO;
import br.ufes.gerenciaimagens.model.Imagem;
import java.util.List;

/**
 *
 * @author rborges
 */
public class ImagemRepository {
    
    private IImagemDAO imagemDAO;
    
    public ImagemRepository() {
        imagemDAO = new ImagemSqliteDAO();
    }
    
    public List<Imagem> obterTodasNaoExcluidas() throws Exception {
        return imagemDAO.obterTodasNaoExcluidas();
    }
    
}
