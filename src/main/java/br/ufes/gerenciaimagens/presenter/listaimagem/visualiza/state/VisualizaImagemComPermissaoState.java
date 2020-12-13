package br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.state;

import br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.VisualizaImagemPresenter;
import br.ufes.gerenciaimagens.service.PermissaoService;

/**
 *
 * @author rborges
 */
public class VisualizaImagemComPermissaoState extends VisualizaImagemPresenterState {
    
    private PermissaoService permissaoService;

    public VisualizaImagemComPermissaoState(VisualizaImagemPresenter presenter) {
        super(presenter);
        
        this.permissaoService = new PermissaoService();
        
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
