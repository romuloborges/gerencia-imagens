package br.ufes.gerenciaimagens.presenter.listaimagem.state;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemView;
import br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.VisualizaImagemPresenter;
import javax.swing.JOptionPane;

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
            visualizar();
        });
    }

    @Override
    public void visualizar() {
        Long idImagemSelecionada = presenter.getIdImagemSelecionada();
        
        if (idImagemSelecionada != null) {
            Imagem imagem = null;
            for(Imagem i : presenter.getImagens()) {
                if (i.getId().equals(idImagemSelecionada)) {
                    imagem = i;
                    break;
                }
            }
            if (imagem != null) {
                new VisualizaImagemPresenter(presenter.getContainer(), presenter.getIdUsuarioLogado(), imagem);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma imagem", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}