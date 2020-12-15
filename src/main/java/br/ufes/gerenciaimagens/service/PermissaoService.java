package br.ufes.gerenciaimagens.service;

import br.ufes.gerenciaimagens.model.TipoPermissao;
import br.ufes.gerenciaimagens.repository.PermissaoRepository;
import java.util.List;

/**
 *
 * @author rborges
 */
public class PermissaoService {
    
    private PermissaoRepository permissaoRepository;
    
    public PermissaoService() {
        this.permissaoRepository = new PermissaoRepository();
    }
    
    public boolean possuiPermissao(Long idUsuario, Long idImagem, TipoPermissao tipoPermissao) throws Exception {
        return permissaoRepository.possuiPermissao(idUsuario, idImagem, tipoPermissao);
    }
    
    public void concederPermissaoAoUsuario(List<TipoPermissao> permissoes, Long idUsuario, Long idImagem) throws Exception {
        permissaoRepository.concederPermissaoAoUsuario(permissoes, idUsuario, idImagem);
    }
    
    public void removerPermissoes(Long idUsuario, Long idImagem) throws Exception {
        permissaoRepository.removerPermissoes(idUsuario, idImagem);
    }
    
    public void salvarPermissoes(List<TipoPermissao> permissoes, Long idUsuario, Long idImagem) throws Exception {
        permissaoRepository.salvarPermissoes(permissoes, idUsuario, idImagem);
    }
    
    public boolean possuiPermissaoEmTodasImagens(Long idUsuario, TipoPermissao tipoPermissao) throws Exception {
        return permissaoRepository.possuiPermissaoEmTodasImagens(idUsuario, tipoPermissao);
    }
    
}
