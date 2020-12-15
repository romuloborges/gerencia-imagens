package br.ufes.gerenciaimagens.dao.factory.notificacao;

import br.ufes.gerenciaimagens.dao.interfaces.INotificacaoDAO;
import br.ufes.gerenciaimagens.dao.sqlite.impl.NotificacaoSqliteDAO;

/**
 *
 * @author rborges
 */
public class NotificacaoSqliteDAOFactory extends NotificacaoDAOFactory {
    
    public NotificacaoSqliteDAOFactory() {
        this.bancoFabricado = "SQLITE";
    }

    @Override
    public INotificacaoDAO cria() {
        return new NotificacaoSqliteDAO();
    }
    
}
