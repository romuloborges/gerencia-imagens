package br.ufes.gerenciaimagens.presenter.principal.state;

import br.ufes.gerenciaimagens.presenter.login.LoginPresenter;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalView;
import br.ufes.gerenciaimagens.service.UsuarioService;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

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
        
        configuraSair();
        
        this.usuarioService = new UsuarioService();
    }
    
    protected abstract void exibeComponentes();
    
    protected void atualizarNotificacoesRodape() {
        
    }
    
    private void configuraSair() {
        PrincipalView view = principalPresenter.getView();
        
        view.getItemSair().addActionListener((ae) -> {
            fechaJanelasAbertas();
            principalPresenter.setState(new TelaPrincipalSemUsuarioState(principalPresenter));
        });
    }
    
    protected void fechaJanelasAbertas() {
        PrincipalView view = principalPresenter.getView();
        JDesktopPane desktop = view.getDesktop();
        
        for(JInternalFrame frame : desktop.getAllFrames()) {
            frame.dispose();
        }
    }
    
}
