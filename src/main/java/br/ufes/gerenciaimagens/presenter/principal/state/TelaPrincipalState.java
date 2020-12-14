package br.ufes.gerenciaimagens.presenter.principal.state;

import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.service.UsuarioService;

/**
 *
 * @author rborges
 */
public abstract class TelaPrincipalState {
    
    protected PrincipalPresenter principalPresenter;
    protected UsuarioService usuarioService;
    
    public TelaPrincipalState(PrincipalPresenter principalPresenter) {
        if (principalPresenter == null) {
            throw new RuntimeException("Presenter não informada");
        }
        
        this.principalPresenter = principalPresenter;
        
        this.usuarioService = new UsuarioService();
    }
    
    protected abstract void exibeComponentes();
    
    protected void atualizarNotificacoesRodape() {
        
    }
    
}
