package br.ufes.gerenciaimagens.presenter.principal.state;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalView;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

/**
 *
 * @author rborges
 */
public class TelaPrincipalUsuarioNormalState extends TelaPrincipalState {
    
    public TelaPrincipalUsuarioNormalState(PrincipalPresenter principalPresenter, Usuario usuarioLogado) {
        super(principalPresenter);
        principalPresenter.setUsuarioLogado(usuarioLogado);
        
        removeListeners();
        adicionaListeners();
        
        exibeComponentes();
    }
    
    private void adicionaListeners() {
        PrincipalView view = principalPresenter.getView();
        Usuario usuarioLogado = principalPresenter.getUsuarioLogado();
        
        view.getItemListarImagem().addActionListener((ae) -> {
            new ListaImagemPresenter(view.getDesktop(), usuarioLogado.getId());
        });
    }
    
    private void removeListeners() {
        PrincipalView view = principalPresenter.getView();
        
        removeActionListeners(view.getItemListarImagem());
    }
    
    private void removeActionListeners(JMenuItem menuItem) {
        for(ActionListener ae : menuItem.getActionListeners()) {
            menuItem.removeActionListener(ae);
        }
    }
    
    @Override
    protected void exibeComponentes() {
        exibirMenu();
        exibirRodape();
    }
    
    protected void exibirRodape() {
        if (principalPresenter.getUsuarioLogado() != null) {
            PrincipalView view = principalPresenter.getView();
            Usuario usuarioLogado = principalPresenter.getUsuarioLogado();
            
            view.getPanelRodape().setVisible(true);
        
            view.getLabelUsuarioLogado().setHorizontalTextPosition(SwingConstants.CENTER);
            view.getLabelUsuarioLogado().setText(usuarioLogado.getNome() + "; Tipo de acesso: " + usuarioLogado.getDescricaoTipo());
            
            atualizarNotificacoesRodape();
        }
    }
    
    protected void exibirMenu() {
        PrincipalView view = principalPresenter.getView();
        
        JMenuBar menuBar = view.getMenuBarPrincipal();
        menuBar.setVisible(true);
        
        exibirSubMenus();
        exibirItensSubMenus();
    }
    
    protected void exibirSubMenus() {
        PrincipalView view = principalPresenter.getView();
        
        JMenu menuImagens = view.getMenuImagem();
        menuImagens.setVisible(true);
        
        JMenu menuUsuarios = view.getMenuUsuario();
        menuUsuarios.setVisible(false);
    }
    
    protected void exibirItensSubMenus() {
        PrincipalView view = principalPresenter.getView();
        
        JMenuItem itemListarImagens = view.getItemListarImagem();
        itemListarImagens.setVisible(true);
        
        JMenuItem itemListarUsuarios = view.getItemListarUsuarios();
        itemListarUsuarios.setVisible(false);
    }
    
}
