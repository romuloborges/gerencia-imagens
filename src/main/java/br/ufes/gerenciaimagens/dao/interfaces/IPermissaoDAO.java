package br.ufes.gerenciaimagens.dao.interfaces;

import br.ufes.gerenciaimagens.model.TipoPermissao;
import java.util.List;

/**
 *
 * @author rborges
 */
public interface IPermissaoDAO {
    
    public boolean possuiPermissao(Long idUsuario, Long idImagem, TipoPermissao tipoPermissao) throws Exception;
    public void concederPermissaoAoUsuario(List<TipoPermissao> permissoes, Long idUsuario, Long idImagem) throws Exception;
    public void removerPermissoes(Long idUsuario, Long idImagem) throws Exception;
    public boolean possuiPermissaoEmTodasImagens(Long idUsuario, TipoPermissao tipoPermissao) throws Exception;
    
}
