package br.ufes.gerenciaimagens.presenter.usuario.manterusuario.state;

import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.ManterUsuarioPresenter;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.ManterUsuarioView;

/**
 *
 * @author rborges
 */
public class VisualizarUsuarioState extends ManterUsuarioState {
    
    public VisualizarUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        
        initCampos();
        initBotoes();
        initListeners();
        setDados(presenter.getUsuarioManter());
    }
    
    private void initCampos() {
        disableCampos();
    }
    
    private void initBotoes() {
        ManterUsuarioView view = presenter.getView();
        
        view.getButtonAcaoState().setText("Habilitar edição");
        view.getButtonAcaoState().setVisible(true);
        view.getButtonFechar().setVisible(true);
    }
    
    private void initListeners() {
        ManterUsuarioView view = presenter.getView();
        
        view.getButtonAcaoState().addActionListener((ae) -> {
            habilitarEdicao();
        });
        
        view.getButtonExcluir().addActionListener((ae) -> {
            excluir();
        });
    }

    @Override
    public void habilitarEdicao() {
        presenter.setState(new EditarUsuarioState(presenter));
    }
    
}
