package br.ufes.gerenciaimagens.presenter;

import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import javax.swing.JFrame;

/**
 *
 * @author rborges
 */
public class PrincipalPresenter {
    
    private PrincipalView view;
    
    public PrincipalPresenter() {
        this.view = new PrincipalView();
        
        this.view.setState(JFrame.ICONIFIED);
        this.view.setLocationRelativeTo(this.view.getParent());
        this.view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.view.getItemListarImagem().addActionListener((ae) -> {
            new ListaImagemPresenter(this.view.getDesktop());
        });
        
        view.setVisible(true);
    }
    
}
