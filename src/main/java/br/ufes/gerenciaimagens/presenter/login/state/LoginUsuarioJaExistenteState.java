package br.ufes.gerenciaimagens.presenter.login.state;

import br.ufes.gerenciaimagens.model.enums.TipoUsuario;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.criptografasenhautil.CriptografaSenhaUtil;
import br.ufes.gerenciaimagens.presenter.login.LoginPresenter;
import br.ufes.gerenciaimagens.presenter.login.LoginView;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.presenter.principal.state.TelaPrincipalUsuarioAdminState;
import br.ufes.gerenciaimagens.presenter.principal.state.TelaPrincipalUsuarioNormalState;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class LoginUsuarioJaExistenteState extends LoginState {
    
    public LoginUsuarioJaExistenteState(LoginPresenter presenter) {
        super(presenter);
        
        initCampos();
        initBotoes();
        initListeners();
    }
    
    private void initCampos() {
        LoginView view = presenter.getView();
        
        view.getLabelLogin().setVisible(true);
        view.getLabelSenha().setVisible(true);
        
        view.getTextLogin().setVisible(true);
        view.getTextSenha().setVisible(true);
        
        view.getScrollPrimeiroUsuario().setVisible(false);
        
        view.getInternalFrame().setSize(405, 180);
    }
    
    private void initBotoes() {
        LoginView view = presenter.getView();
        
        view.getButtonAcao().setText("Logar");
    }
    
    private void initListeners() {
        LoginView view = presenter.getView();
        
        view.getButtonAcao().addActionListener((ae) -> {
            realizarLogin();
        });
    }
    
    private void realizarLogin() {
        LoginView view = presenter.getView();
        String login = view.getTextLogin().getText();
        String senha = new String(view.getTextSenha().getPassword());
        
        try {
            senha = CriptografaSenhaUtil.getInstancia().criptografar(senha);
            Usuario usuario = usuarioService.getByLogin(login, senha);
            if (usuario != null && usuario.getId() != null) {
                view.dispose();
                PrincipalPresenter principalPresenter = presenter.getPrincipalPresenter();
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
