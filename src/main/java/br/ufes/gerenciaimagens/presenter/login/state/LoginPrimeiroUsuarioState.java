package br.ufes.gerenciaimagens.presenter.login.state;

import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioPresenter;
import br.ufes.gerenciaimagens.presenter.login.LoginPresenter;
import br.ufes.gerenciaimagens.presenter.login.LoginView;

/**
 *
 * @author rborges
 */
public class LoginPrimeiroUsuarioState extends LoginState {
    
    public LoginPrimeiroUsuarioState(LoginPresenter presenter) {
        super(presenter);
        
        initCampos();
        initBotoes();
        initListeners();
    }
    
    private void initCampos() {
        LoginView view = presenter.getView();
        
        view.getLabelLogin().setVisible(false);
        view.getLabelSenha().setVisible(false);
        
        view.getTextLogin().setVisible(false);
        view.getTextSenha().setVisible(false);
        
        view.getTextPrimeiroUsuario().setText("Você é o primeiro usuário a acessar o sistema. Por favor realize seu cadastro");
        view.getTextPrimeiroUsuario().setWrapStyleWord(true);
        view.getTextPrimeiroUsuario().setLineWrap(true);
        view.getTextPrimeiroUsuario().setOpaque(false);
        view.getTextPrimeiroUsuario().setEditable(false);
        view.getTextPrimeiroUsuario().setFocusable(false);
        view.getScrollPrimeiroUsuario().setVisible(true);
    }
    
    private void initBotoes() {
        LoginView view = presenter.getView();
        
        view.getButtonAcao().setText("Cadastrar-se");
    }
    
    private void initListeners() {
        LoginView view = presenter.getView();
        
        view.getButtonAcao().addActionListener((ae) -> {
            realizarCadastro();
        });
    }
    
    private void realizarCadastro() {
        LoginView view = presenter.getView();
        
        view.dispose();
        new ManterUsuarioPresenter(presenter.getContainer(), presenter.getPrincipalPresenter());
    }
    
}
