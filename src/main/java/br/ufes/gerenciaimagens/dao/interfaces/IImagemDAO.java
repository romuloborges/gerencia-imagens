package br.ufes.gerenciaimagens.dao.interfaces;

import br.ufes.gerenciaimagens.model.Imagem;
import java.util.List;

/**
 *
 * @author rborges
 */
public interface IImagemDAO {
    
    public List<Imagem> obterTodasNaoExcluidas() throws Exception;
    public void excluir(Long id) throws Exception;
    public void restaurar(Long id) throws Exception;
    
}
