package br.ufes.gerenciaimagens.repository;

import br.ufes.gerenciaimagens.dao.collection.ImagemDAOCollection;
import br.ufes.gerenciaimagens.dao.interfaces.IImagemDAO;
import br.ufes.gerenciaimagens.model.Imagem;
import java.util.List;

/**
 *
 * @author rborges
 */
public class ImagemRepository {
    
    private IImagemDAO imagemDAO;
    
    public ImagemRepository() {
        imagemDAO = ImagemDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }
    
    public List<Imagem> obterTodasNaoExcluidas() throws Exception {
        return imagemDAO.obterTodasNaoExcluidas();
    }
    
    public void excluir(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID da imagem não foi fornecido");
        }
        
        imagemDAO.excluir(id);
    }
    
}
