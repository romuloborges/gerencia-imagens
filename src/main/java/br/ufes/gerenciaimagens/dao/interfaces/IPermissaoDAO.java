package br.ufes.gerenciaimagens.dao.interfaces;

/**
 *
 * @author rborges
 */
public interface IPermissaoDAO {
    
    public boolean possuiPermissaoDeVisualizar(Long idUsuario, Long idImagem) throws Exception;
    public boolean possuiPermissaoDeExcluir(Long idUsuario, Long idImagem) throws Exception;
    public boolean possuiPermissaoDeCompartilhar(Long idUsuario, Long idImagem) throws Exception;
    
}
