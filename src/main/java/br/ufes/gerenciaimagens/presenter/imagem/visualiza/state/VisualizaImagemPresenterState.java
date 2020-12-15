package br.ufes.gerenciaimagens.presenter.imagem.visualiza.state;

import br.ufes.gerenciaimagens.presenter.imagem.visualiza.VisualizaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.VisualizaImagemView;

/**
 *
 * @author rborges
 */
public abstract class VisualizaImagemPresenterState {
    
    protected VisualizaImagemPresenter presenter;
    
    public VisualizaImagemPresenterState(VisualizaImagemPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter informada é inválida");
        }
        
        this.presenter = presenter;
        
    }
    
    protected abstract void initComponents();
    
    public abstract void solicitarAcesso();
    
    protected void fechar() {
        VisualizaImagemView view = presenter.getView();
        view.dispose();
    }
    
}
