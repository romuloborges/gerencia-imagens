package br.ufes.gerenciaimagens.presenter.listaimagem.state;

import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemView;
import br.ufes.gerenciaimagens.service.ImagemService;
import javax.swing.JButton;

/**
 *
 * @author rborges
 */
public abstract class ListaImagemState {
    
    protected ListaImagemPresenter presenter;
    protected ImagemService imagemService;
    
    public ListaImagemState(ListaImagemPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter não informada");
        }
        
        this.presenter = presenter;
        
        removeListeners();
        
        this.imagemService = new ImagemService();
    }
    
    public void fechar() {
        presenter.getView().setVisible(false);
        presenter.getView().dispose();
    }
    
    private void removeListeners() {
        ListaImagemView view = presenter.getView();
        
        removerActionListeners(presenter.getView().getButtonCompartilhar());
        removerActionListeners(presenter.getView().getButtonExcluir());
        removerActionListeners(presenter.getView().getButtonGerenciarPermissoes());
        removerActionListeners(presenter.getView().getButtonVisualizar());
    }
    
    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
    
}
