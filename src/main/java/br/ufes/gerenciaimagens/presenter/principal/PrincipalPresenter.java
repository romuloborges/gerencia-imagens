package br.ufes.gerenciaimagens.presenter.principal;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.ListaUsuarioPresenter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author rborges
 */
public class PrincipalPresenter {
    
    private PrincipalView view;
    private Usuario usuarioLogado;
    
    public PrincipalPresenter(Usuario usuarioLogado) {
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível identificar o usuário logado", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.view = new PrincipalView();
        this.usuarioLogado = usuarioLogado;
        
        this.view.setState(JFrame.ICONIFIED);
        this.view.setLocationRelativeTo(this.view.getParent());
        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.view.getItemListarImagem().addActionListener((ae) -> {
            new ListaImagemPresenter(this.view.getDesktop(), usuarioLogado.getId());
        });
        
        this.view.getItemManterUsuarios().addActionListener((ae) -> {
            new ListaUsuarioPresenter(this.view.getDesktop(), usuarioLogado.getId());
        });
        
        configurarLabelUsuarioLogado();
        
        view.setVisible(true);
    }
    
    private void configurarLabelUsuarioLogado() {
        this.view.getLabelUsuarioLogado().setHorizontalTextPosition(SwingConstants.CENTER);
        this.view.getLabelUsuarioLogado().setText(usuarioLogado.getNome() + "; Tipo de acesso: " + usuarioLogado.getDescricaoTipo());
    }
    
}
