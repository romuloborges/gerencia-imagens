package br.ufes.gerenciaimagens.presenter.principal;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.principal.state.TelaPrincipalSemUsuarioState;
import br.ufes.gerenciaimagens.presenter.principal.state.TelaPrincipalState;
import javax.swing.JFrame;

/**
 *
 * @author rborges
 */
public class PrincipalPresenter {
    
    private PrincipalView view;
    private Usuario usuarioLogado;
    private TelaPrincipalState state;
    
    public PrincipalPresenter() {
        this.view = new PrincipalView();
        
        this.view.setState(JFrame.ICONIFIED);
        this.view.setLocationRelativeTo(this.view.getParent());
        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        view.setVisible(true);
        
        setState(new TelaPrincipalSemUsuarioState(this));
    }

    public PrincipalView getView() {
        return view;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public TelaPrincipalState getState() {
        return state;
    }

    public void setState(TelaPrincipalState state) {
        this.state = state;
    }
    
}
