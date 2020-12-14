package br.ufes.gerenciaimagens.presenter.login;

import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.login.state.LoginPrimeiroUsuarioState;
import br.ufes.gerenciaimagens.presenter.login.state.LoginState;
import br.ufes.gerenciaimagens.presenter.login.state.LoginUsuarioJaExistenteState;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.service.UsuarioService;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class LoginPresenter extends BaseInternalFramePresenter<LoginView> {
    
    private UsuarioService usuarioService;
    private PrincipalPresenter principalPresenter;
    private LoginState state;
    
    public LoginPresenter(PrincipalPresenter principalPresenter, JDesktopPane desktop) {
        super(desktop, new LoginView());
        this.principalPresenter = principalPresenter;
        
        usuarioService = new UsuarioService();
        initState();
        
        getView().setVisible(true);
    }
    
    private void initState() {
        try {
            if (usuarioService.existeAdministradorAtivo()) {
                setState(new LoginUsuarioJaExistenteState(this));
            } else {
                setState(new LoginPrimeiroUsuarioState(this));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public PrincipalPresenter getPrincipalPresenter() {
        return principalPresenter;
    }

    public void setState(LoginState state) {
        this.state = state;
    }
    
}
