package br.ufes.gerenciaimagens.presenter.login;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.service.UsuarioService;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class LoginPresenter {
    
    private LoginView view;
    private UsuarioService usuarioService;
    
    private void realizarLogin() {
        var login = view.getTextLogin().getText();
        var senha = new String(view.getTextSenha().getPassword());

        try {
            Usuario usuario = usuarioService.getByLogin(login, senha);
            if (usuario != null && usuario.getId() != null) {
//                new TelaInicialPresenter(usuario);
                view.setVisible(false);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login Inválido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
