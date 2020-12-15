package br.ufes.gerenciaimagens.repository;

import br.ufes.gerenciaimagens.dao.collection.PermissaoDAOCollection;
import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;
import br.ufes.gerenciaimagens.model.TipoPermissao;
import java.util.List;

/**
 *
 * @author rborges
 */
public class PermissaoRepository {
    
    private IPermissaoDAO permissaoDAO;
    
    public PermissaoRepository() {
        permissaoDAO = PermissaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }
    
    public boolean possuiPermissao(Long idUsuario, Long idImagem, TipoPermissao tipoPermissao) throws Exception {
        if (idUsuario == null) {
            throw new Exception("Não foi possível identificar o usuário");
        }
        
        if (tipoPermissao == null) {
            throw new Exception("Não foi possível identificar a permissão");
        }
        
        return permissaoDAO.possuiPermissao(idUsuario, idImagem, tipoPermissao);
    }
    
    public void concederPermissaoAoUsuario(List<TipoPermissao> permissoes, Long idUsuario, Long idImagem) throws Exception {
        if (permissoes == null) {
            throw new Exception("Permissões não informadas");
        }
        
        if (idUsuario == null) {
            throw new Exception("Usuário informado é inválido");
        }
        
        permissaoDAO.concederPermissaoAoUsuario(permissoes, idUsuario, idImagem);
    }
    
    public void removerPermissoes(Long idUsuario, Long idImagem) throws Exception {
        if (idUsuario == null) {
            throw new Exception("Usuário não informado");
        }
        
        permissaoDAO.removerPermissoes(idUsuario, idImagem);
    }
    
    public void salvarPermissoes(List<TipoPermissao> permissoes, Long idUsuario, Long idImagem) throws Exception {
        removerPermissoes(idUsuario, idImagem);
        concederPermissaoAoUsuario(permissoes, idUsuario, idImagem);
    }
    
    public boolean possuiPermissaoEmTodasImagens(Long idUsuario, TipoPermissao tipoPermissao) throws Exception {
        if (idUsuario == null) {
            throw new Exception("ID do Usuário fornecido é inválido");
        }
        
        if (tipoPermissao == null) {
            throw new Exception("Tipo de permissão fornecido é inválido");
        }
        
        return permissaoDAO.possuiPermissaoEmTodasImagens(idUsuario, tipoPermissao);
    }
    
}
