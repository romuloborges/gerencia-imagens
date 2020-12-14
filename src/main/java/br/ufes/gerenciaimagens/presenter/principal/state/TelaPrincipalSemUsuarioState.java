package br.ufes.gerenciaimagens.presenter.principal.state;

import br.ufes.gerenciaimagens.presenter.login.LoginPresenter;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalView;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 *
 * @author rborges
 */
public class TelaPrincipalSemUsuarioState extends TelaPrincipalState {

    public TelaPrincipalSemUsuarioState(PrincipalPresenter principalPresenter) {
        super(principalPresenter);
        
        fechaJanelasAbertas();
        exibeComponentes();
    }
    
    @Override
    protected void exibeComponentes() {
        PrincipalView view = principalPresenter.getView();
        
        view.getPanelRodape().setVisible(false);
        view.getMenuBarPrincipal().setVisible(false);
        
        new LoginPresenter(principalPresenter, view.getDesktop());
    }
    
    private void fechaJanelasAbertas() {
        PrincipalView view = principalPresenter.getView();
        JDesktopPane desktop = view.getDesktop();
        
        for(JInternalFrame frame : desktop.getAllFrames()) {
            frame.dispose();
        }
    }
    
}
