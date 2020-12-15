package br.ufes.gerenciaimagens.presenter.listaimagem.state;

import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemView;

/**
 *
 * @author rborges
 */
public class ListagemImagensState extends ListaImagemState {
    
    public ListagemImagensState(ListaImagemPresenter presenter) {
        super(presenter);
        
        initBotoes();
        initListeners();
    }
    
    private void initBotoes() {
        ListaImagemView view = presenter.getView();
        
        view.getButtonCompartilhar().setVisible(true);
        view.getButtonExcluir().setVisible(true);
        view.getButtonVisualizar().setVisible(true);
        view.getButtonGerenciarPermissoes().setVisible(false);
    }
    
    private void initListeners() {
        ListaImagemView view = presenter.getView();
        
        view.getButtonCompartilhar().addActionListener((ae) -> {
            
        });
        
        view.getButtonExcluir().addActionListener((ae) -> {
            
        });
        
        view.getButtonVisualizar().addActionListener((ae) -> {
            
        });
    }
    
}