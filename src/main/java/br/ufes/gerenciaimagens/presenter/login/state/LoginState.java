package br.ufes.gerenciaimagens.presenter.login.state;

import br.ufes.gerenciaimagens.presenter.login.LoginPresenter;
import br.ufes.gerenciaimagens.service.UsuarioService;
import javax.swing.JButton;

/**
 *
 * @author rborges
 */
public abstract class LoginState {
    
    protected LoginPresenter presenter;
    protected UsuarioService usuarioService;

    public LoginState(LoginPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter não informada");
        }
        
        this.presenter = presenter;
        
        removeListeners();
        
        this.usuarioService = new UsuarioService();
    }
    
    public void logar() {
        
    }
    
    public void cadastrar() {
        
    }
    
    private void removeListeners() {
        removerActionListeners(presenter.getView().getButtonAcao());
    }
    
    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
    
}
