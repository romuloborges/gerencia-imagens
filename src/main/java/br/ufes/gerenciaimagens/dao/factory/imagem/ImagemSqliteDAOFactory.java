package br.ufes.gerenciaimagens.dao.factory.imagem;

import br.ufes.gerenciaimagens.dao.interfaces.IImagemDAO;
import br.ufes.gerenciaimagens.dao.sqlite.impl.ImagemSqliteDAO;

/**
 *
 * @author rborges
 */
public class ImagemSqliteDAOFactory extends ImagemDAOFactory {
    
    public ImagemSqliteDAOFactory() {
        this.bancoFabricado = "SQLITE";
    }

    @Override
    public IImagemDAO cria() {
        return new ImagemSqliteDAO();
    }
    
}
