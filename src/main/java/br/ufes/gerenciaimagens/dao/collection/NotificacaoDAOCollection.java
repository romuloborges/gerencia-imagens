package br.ufes.gerenciaimagens.dao.collection;

import br.ufes.gerenciaimagens.dao.factory.notificacao.NotificacaoDAOFactory;
import br.ufes.gerenciaimagens.dao.factory.notificacao.NotificacaoSqliteDAOFactory;
import br.ufes.gerenciaimagens.dao.interfaces.INotificacaoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rborges
 */
public class NotificacaoDAOCollection {
    
    private List<NotificacaoDAOFactory> factories = new ArrayList<>();
    private static NotificacaoDAOCollection instancia;
    
    private NotificacaoDAOCollection() {
        addFactory(new NotificacaoSqliteDAOFactory());
    }
    
    public static NotificacaoDAOCollection getInstancia() {
        if (instancia == null) {
            instancia = new NotificacaoDAOCollection();
        }
        
        return instancia;
    }
    
    public void addFactory(NotificacaoDAOFactory factory) {
        if (factory == null) {
            throw new RuntimeException("Factory fornecida é inválida");
        }
        
        if(factories.stream().filter(f -> f.getClass().equals(factory.getClass())).findAny().isPresent()) {
            throw new RuntimeException("Esta factory já foi adicionada");
        }
        
        this.factories.add(factory);
    }
    
    public INotificacaoDAO cria(String banco) {
        if (banco == null || banco.isBlank()) {
            throw new RuntimeException("Banco fornecido é inválido");
        }
        
        for(NotificacaoDAOFactory factory : factories) {
            if (factory.aceita(banco)) {
                return factory.cria();
            }
        }
        
        return null;
    }
    
}
