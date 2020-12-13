package br.ufes.gerenciaimagens.service;

import br.ufes.gerenciaimagens.repository.PermissaoRepository;

/**
 *
 * @author rborges
 */
public class PermissaoService {
    
    private PermissaoRepository permissaoRepository;
    
    public PermissaoService() {
        this.permissaoRepository = new PermissaoRepository();
    }
    
    public boolean possuiPermissaoDeVisualizar(Long idUsuario, Long idImagem) throws Exception {
        return permissaoRepository.possuiPermissaoDeVisualizar(idUsuario, idImagem);
    }
    
    public boolean possuiPermissaoDeExcluir(Long idUsuario, Long idImagem) throws Exception {
        return permissaoRepository.possuiPermissaoDeExcluir(idUsuario, idImagem);
    }
    
    public boolean possuiPermissaoDeCompartilhar(Long idUsuario, Long idImagem) throws Exception {
        return permissaoRepository.possuiPermissaoDeCompartilhar(idUsuario, idImagem);
    }
    
}
