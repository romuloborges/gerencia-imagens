package br.ufes.gerenciaimagens.dao.factory.imagem;

import br.ufes.gerenciaimagens.dao.interfaces.IImagemDAO;

/**
 *
 * @author rborges
 */
public abstract class ImagemDAOFactory {
    
    protected String bancoFabricado;
    
    public abstract IImagemDAO cria();
    
    public final boolean aceita(String banco) {
        return banco.equalsIgnoreCase(bancoFabricado);
    }
    
}
