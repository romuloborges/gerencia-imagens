package br.ufes.gerenciaimagens.main;

import br.ufes.gerenciaimagens.model.enums.TipoUsuario;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author rborges
 */
public class Main {
    
    public static void main(String[] args) {
        Main app = new Main();
        Properties prop = app.loadPropertiesFile("config.properties");
        System.setProperty("db.name", prop.getProperty("db.name"));
        
        new PrincipalPresenter();
    }
    
    public Properties loadPropertiesFile(String filePath) {
        Properties prop = new Properties();

        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Não foi possível carregar o arquivo de properties : " + filePath);
        }

        return prop;
    }
    
}
