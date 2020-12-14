package br.ufes.gerenciaimagens.presenter.login;

import br.ufes.gerenciaimagens.model.TipoUsuario;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.criptografasenhautil.CriptografaSenhaUtil;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.presenter.principal.state.TelaPrincipalUsuarioAdminState;
import br.ufes.gerenciaimagens.presenter.principal.state.TelaPrincipalUsuarioNormalState;
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
    
    public LoginPresenter(PrincipalPresenter principalPresenter, JDesktopPane desktop) {
        super(desktop, new LoginView());
        this.principalPresenter = principalPresenter;
        
        initListeners();
        usuarioService = new UsuarioService();
        
        getView().setVisible(true);
    }
    
    private void initListeners() {
        getView().getButtonLogar().addActionListener((ae) -> {
            realizarLogin();
        });
    }
    
    private void realizarLogin() {
        LoginView view = getView();
        String login = view.getTextLogin().getText();
        String senha = new String(view.getTextSenha().getPassword());
        
        try {
            senha = CriptografaSenhaUtil.getInstancia().criptografar(senha);
            Usuario usuario = usuarioService.getByLogin(login, senha);
            if (usuario != null && usuario.getId() != null) {
                view.dispose();
                if (TipoUsuario.NORMAL.equals(usuario.getTipo())) {
                    principalPresenter.setState(new TelaPrincipalUsuarioNormalState(principalPresenter, usuario));
                } else if (TipoUsuario.ADMINISTRADOR.equals(usuario.getTipo())) {
                    principalPresenter.setState(new TelaPrincipalUsuarioAdminState(principalPresenter, usuario));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Login Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
