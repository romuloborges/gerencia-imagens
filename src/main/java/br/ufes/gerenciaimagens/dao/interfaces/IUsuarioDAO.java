package br.ufes.gerenciaimagens.dao.interfaces;

import br.ufes.gerenciaimagens.model.Usuario;
import java.util.List;

/**
 *
 * @author rborges
 */
public interface IUsuarioDAO {
    
    public void insert(Usuario usuario) throws Exception;
    public void update(Usuario usuario) throws Exception;
    public Usuario getById(Long id) throws Exception;
    public Usuario getByLogin(String login, String senha) throws Exception;
    public void delete(Long id) throws Exception;
    public boolean loginExists(String login) throws Exception;
    public List<Usuario> filter(String nome) throws Exception;
    
}
