package br.ufes.gerenciaimagens.dao.interfaces;

import br.ufes.gerenciaimagens.model.TipoPermissao;

/**
 *
 * @author rborges
 */
public interface IPermissaoDAO {
    
    public boolean possuiPermissaoDeVisualizar(Long idUsuario, Long idImagem) throws Exception;
    public boolean possuiPermissaoDeExcluir(Long idUsuario, Long idImagem) throws Exception;
    public boolean possuiPermissaoDeCompartilhar(Long idUsuario, Long idImagem) throws Exception;
    public void concederPermissaoAoUsuario(TipoPermissao permissao, Long idUsuario, Long idImagem) throws Exception;
    
}
