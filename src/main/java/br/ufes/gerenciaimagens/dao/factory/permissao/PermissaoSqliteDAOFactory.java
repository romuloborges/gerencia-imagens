package br.ufes.gerenciaimagens.dao.factory.permissao;

import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;
import br.ufes.gerenciaimagens.dao.sqlite.impl.PermissaoSqliteDAO;

/**
 *
 * @author rborges
 */
public class PermissaoSqliteDAOFactory extends PermissaoDAOFactory {
    
    public PermissaoSqliteDAOFactory() {
        this.bancoFabricado = "SQLITE";
    }

    @Override
    public IPermissaoDAO cria() {
        return new PermissaoSqliteDAO();
    }
    
}
