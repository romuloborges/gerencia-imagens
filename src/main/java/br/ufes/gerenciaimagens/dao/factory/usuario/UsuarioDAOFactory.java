package br.ufes.gerenciaimagens.dao.factory.usuario;

import br.ufes.gerenciaimagens.dao.interfaces.IUsuarioDAO;

/**
 *
 * @author rborges
 */
public abstract class UsuarioDAOFactory {
    
    protected String bancoFabricado;
    
    public abstract IUsuarioDAO cria();
    
    public final boolean aceita(String banco) {
        return banco.equalsIgnoreCase(bancoFabricado);
    }
    
}
