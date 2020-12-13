package br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.state;

import br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.VisualizaImagemPresenter;

/**
 *
 * @author rborges
 */
public class SolicitaPermissaoVisualizarState extends VisualizaImagemPresenterState {

    public SolicitaPermissaoVisualizarState(VisualizaImagemPresenter presenter) {
        super(presenter);
        
        initComponents();
    }
    
    @Override
    protected void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void compartilhar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void solicitarAcesso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
