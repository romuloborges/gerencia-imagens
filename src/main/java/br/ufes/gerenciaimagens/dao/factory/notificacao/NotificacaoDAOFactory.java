package br.ufes.gerenciaimagens.dao.factory.notificacao;

import br.ufes.gerenciaimagens.dao.interfaces.INotificacaoDAO;

/**
 *
 * @author rborges
 */
public abstract class NotificacaoDAOFactory {
    
    protected String bancoFabricado;
    
    public abstract INotificacaoDAO cria();
    
    public final boolean aceita(String banco) {
        return banco.equalsIgnoreCase(bancoFabricado);
    }
    
}
