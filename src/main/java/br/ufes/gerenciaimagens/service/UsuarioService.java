package br.ufes.gerenciaimagens.service;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.repository.UsuarioRepository;
import java.util.List;

/**
 *
 * @author rborges
 */
public class UsuarioService {
    
    private UsuarioRepository usuarioRepository;
    
    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }
    
    public void insert(Usuario usuario) throws Exception {
        usuarioRepository.insert(usuario);
    }
    
    public void update(Usuario usuario) throws Exception {
        usuarioRepository.update(usuario);
    }
    
    public Usuario getById(Long id) throws Exception {
        return usuarioRepository.getById(id);
    }
    
    public Usuario getByLogin(String login, String senha) throws Exception {
        return usuarioRepository.getByLogin(login, senha);
    }
    
    public void delete(Long id) throws Exception {
        usuarioRepository.delete(id);
    }
    
    public boolean loginExists(String login) throws Exception {
        return usuarioRepository.loginExists(login);
    }
    
    public List<Usuario> filter(String nome) throws Exception {
        return usuarioRepository.filter(nome);
    }
    
}
