package br.ufes.gerenciaimagens.repository;

import br.ufes.gerenciaimagens.dao.collection.PermissaoDAOCollection;
import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;
import br.ufes.gerenciaimagens.model.TipoPermissao;

/**
 *
 * @author rborges
 */
public class PermissaoRepository {
    
    private IPermissaoDAO permissaoDAO;
    
    public PermissaoRepository() {
        permissaoDAO = PermissaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }
    
    public boolean possuiPermissaoDeVisualizar(Long idUsuario, Long idImagem) throws Exception {
        if (idUsuario == null) {
            throw new Exception("Não foi possível identificar o usuário");
        }
        
        if (idImagem == null) {
            throw new Exception("Não foi possível identificar a imagem");
        }
        
        return permissaoDAO.possuiPermissaoDeVisualizar(idUsuario, idImagem);
    }
    
    public boolean possuiPermissaoDeExcluir(Long idUsuario, Long idImagem) throws Exception {
        if (idUsuario == null) {
            throw new Exception("Não foi possível identificar o usuário");
        }
        
        if (idImagem == null) {
            throw new Exception("Não foi possível identificar a imagem");
        }
        
        return permissaoDAO.possuiPermissaoDeExcluir(idUsuario, idImagem);
    }
    
    public boolean possuiPermissaoDeCompartilhar(Long idUsuario, Long idImagem) throws Exception {
        if (idUsuario == null) {
            throw new Exception("Não foi possível identificar o usuário");
        }
        
        if (idImagem == null) {
            throw new Exception("Não foi possível identificar a imagem");
        }
        
        return permissaoDAO.possuiPermissaoDeCompartilhar(idUsuario, idImagem);
    }
    
    public void concederPermissaoAoUsuario(TipoPermissao permissao, Long idUsuario, Long idImagem) throws Exception {
        if (permissao == null) {
            throw new Exception("Permissão informada é inválida");
        }
        
        if (idUsuario == null) {
            throw new Exception("Usuário informado é inválido");
        }
        
        permissaoDAO.concederPermissaoAoUsuario(permissao, idUsuario, idImagem);
    }
    
}
