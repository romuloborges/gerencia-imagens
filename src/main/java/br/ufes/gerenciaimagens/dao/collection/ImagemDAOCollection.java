package br.ufes.gerenciaimagens.dao.collection;

import br.ufes.gerenciaimagens.dao.interfaces.IImagemDAO;
import br.ufes.gerenciaimagens.dao.factory.imagem.ImagemDAOFactory;
import br.ufes.gerenciaimagens.dao.factory.imagem.ImagemSqliteDAOFactory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rborges
 */
public class ImagemDAOCollection {
    
    private List<ImagemDAOFactory> factories = new ArrayList<>();
    private static ImagemDAOCollection instancia;
    
    private ImagemDAOCollection() {
        addFactory(new ImagemSqliteDAOFactory());
    }
    
    public static ImagemDAOCollection getInstancia() {
        if (instancia == null) {
            instancia = new ImagemDAOCollection();
        }
        
        return instancia;
    }
    
    public void addFactory(ImagemDAOFactory factory) {
        if (factory == null) {
            throw new RuntimeException("Factory fornecida é inválida");
        }
        
        if(factories.stream().filter(f -> f.getClass().equals(factory.getClass())).findAny().isPresent()) {
            throw new RuntimeException("Esta factory já foi adicionada");
        }
        
        this.factories.add(factory);
    }
    
    public IImagemDAO cria(String banco) {
        if (banco == null || banco.isBlank()) {
            throw new RuntimeException("Banco fornecido é inválido");
        }
        
        for(ImagemDAOFactory factory : factories) {
            if (factory.aceita(banco)) {
                return factory.cria();
            }
        }
        
        return null;
    }
    
}
