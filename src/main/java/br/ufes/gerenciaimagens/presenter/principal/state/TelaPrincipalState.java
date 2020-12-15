package br.ufes.gerenciaimagens.presenter.principal.state;

import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalView;
import br.ufes.gerenciaimagens.service.NotificacaoService;
import br.ufes.gerenciaimagens.service.UsuarioService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public abstract class TelaPrincipalState {
    
    protected PrincipalPresenter principalPresenter;
    protected UsuarioService usuarioService;
    protected NotificacaoService notificacaoService;
    
    public TelaPrincipalState(PrincipalPresenter principalPresenter) {
        if (principalPresenter == null) {
            throw new RuntimeException("Presenter não informada");
        }
        
        this.principalPresenter = principalPresenter;
        this.notificacaoService = new NotificacaoService();
        
        configuraSair();
        
        this.usuarioService = new UsuarioService();
    }
    
    protected abstract void exibeComponentes();
    
    protected void atualizarNotificacoesRodape() {
        try {
            Long qtdNotificacoesNaoLidas = notificacaoService.contaNotificacoesNaoLidas(principalPresenter.getUsuarioLogado().getId());
            PrincipalView view = principalPresenter.getView();
            
            view.getButtonNotificacoes().setText(qtdNotificacoesNaoLidas.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
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
