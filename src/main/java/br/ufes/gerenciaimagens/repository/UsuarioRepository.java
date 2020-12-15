package br.ufes.gerenciaimagens.repository;

import br.ufes.gerenciaimagens.dao.collection.PermissaoDAOCollection;
import br.ufes.gerenciaimagens.dao.collection.UsuarioDAOCollection;
import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;
import br.ufes.gerenciaimagens.dao.interfaces.IUsuarioDAO;
import br.ufes.gerenciaimagens.model.Usuario;
import java.util.List;

/**
 *
 * @author rborges
 */
public class UsuarioRepository {
    
    private IUsuarioDAO usuarioDAO;
    private IPermissaoDAO permissaoDAO;
    
    public UsuarioRepository() {
        usuarioDAO = UsuarioDAOCollection.getInstancia().cria(System.getProperty("db.name"));
        permissaoDAO = PermissaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }
    
    public void insert(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("Usuário informado é inválido");
        } else if (usuario.getId() != null) {
            throw new Exception("Usuário para cadastro não pode conter o atributo ID preenchido");
        } else if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new Exception("Login informado é inválido");
        } else if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new Exception("Senha informada é inválida");
        } else if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new Exception("Nome informado é inválido");
        } else if (usuario.getTipo() == null) {
            throw new Exception("Tipo do usuário informado é inválido");
        }
        
        if (loginExists(usuario.getLogin())) {
            throw new Exception("Este login já está sendo usado");
        }
        
        usuarioDAO.insert(usuario);
    }
    
    public void update(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new Exception("Usuário informado é inválido");
        } else if (usuario.getId() == null) {
            throw new Exception("Usuário para atualização não pode conter o atributo ID nulo");
        } else if (usuario.getLogin() == null || usuario.getLogin().isBlank()) {
            throw new Exception("Login informado é inválido");
        } else if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new Exception("Senha informada é inválida");
        } else if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new Exception("Nome informado é inválido");
        } else if (usuario.getTipo() == null) {
            throw new Exception("Tipo do usuário informado é inválido");
        }
        
        usuarioDAO.update(usuario);
    }
    
    public Usuario getById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID informado é inválido");
        }
        
        return usuarioDAO.getById(id);
    }
    
    public Usuario getByLogin(String login, String senha) throws Exception {
        if (login == null || login.isBlank()) {
            throw new Exception("Login informado é inválido");
        }
        
        if (senha == null || senha.isBlank()) {
            throw new Exception("Senha informada é inválida");
        }
        
        return usuarioDAO.getByLogin(login, senha);
    }
    
    public void delete(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID informado é inválido");
        }
        
        usuarioDAO.delete(id);
    }
    
    public boolean loginExists(String login) throws Exception {
        if (login == null || login.isBlank()) {
            throw new Exception("Login informado é inválido");
        }
        
        return usuarioDAO.loginExists(login);
    }
    
    public List<Usuario> filter(String nome) throws Exception {
        return usuarioDAO.filter(nome);
    }
    
    public boolean existemOutrosAdministradoresAtivos(Long idUsuario) throws Exception {
        return usuarioDAO.existemOutrosAdministradoresAtivos(idUsuario);
    }
    
    public boolean existemOutrosUsuariosAtivos(Long idUsuario) throws Exception {
        return usuarioDAO.existemOutrosUsuariosAtivos(idUsuario);
    }
    
    public boolean existeAdministradorAtivo() throws Exception {
        return usuarioDAO.existeAdministradorAtivo();
    }
    
}
