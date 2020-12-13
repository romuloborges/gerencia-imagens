package br.ufes.gerenciaimagens.dao.factory.permissao;

import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;

/**
 *
 * @author rborges
 */
public abstract class PermissaoDAOFactory {
    
    protected String bancoFabricado;
    
    public abstract IPermissaoDAO cria();
    
    public final boolean aceita(String banco) {
        return banco.equalsIgnoreCase(bancoFabricado);
    }
    
}
