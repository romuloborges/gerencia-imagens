package br.ufes.gerenciaimagens.dao.collection;

import br.ufes.gerenciaimagens.dao.factory.permissao.PermissaoDAOFactory;
import br.ufes.gerenciaimagens.dao.factory.permissao.PermissaoSqliteDAOFactory;
import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rborges
 */
public class PermissaoDAOCollection {
    
    private List<PermissaoDAOFactory> factories = new ArrayList<>();
    private static PermissaoDAOCollection instancia;
    
    private PermissaoDAOCollection() {
        addFactory(new PermissaoSqliteDAOFactory());
    }
    
    public static PermissaoDAOCollection getInstancia() {
        if (instancia == null) {
            instancia = new PermissaoDAOCollection();
        }
        
        return instancia;
    }
    
    public void addFactory(PermissaoDAOFactory factory) {
        if (factory == null) {
            throw new RuntimeException("Factory fornecida é inválida");
        }
        
        if(factories.stream().filter(f -> f.getClass().equals(factory.getClass())).findAny().isPresent()) {
            throw new RuntimeException("Esta factory já foi adicionada");
        }
        
        this.factories.add(factory);
    }
    
    public IPermissaoDAO cria(String banco) {
        if (banco == null || banco.isBlank()) {
            throw new RuntimeException("Banco fornecido é inválido");
        }
        
        for(PermissaoDAOFactory factory : factories) {
            if (factory.aceita(banco)) {
                return factory.cria();
            }
        }
        
        return null;
    }
    
}
