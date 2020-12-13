package br.ufes.gerenciaimagens.dao.factory.usuario;

import br.ufes.gerenciaimagens.dao.interfaces.IUsuarioDAO;
import br.ufes.gerenciaimagens.dao.sqlite.impl.UsuarioSqliteDAO;

/**
 *
 * @author rborges
 */
public class UsuarioSqliteDAOFactory extends UsuarioDAOFactory {
    
    public UsuarioSqliteDAOFactory() {
        this.bancoFabricado = "SQLITE";
    }

    @Override
    public IUsuarioDAO cria() {
        return new UsuarioSqliteDAO();
    }
    
}
