package br.ufes.gerenciaimagens.presenter.listaimagem.state;

import br.ufes.gerenciaimagens.presenter.gerenciarpermissao.GerenciarPermissaoPresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemView;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class ConcederPermissaoState extends ListaImagemState {
    
    public ConcederPermissaoState(ListaImagemPresenter presenter) {
        super(presenter);
        
        initBotoes();
        initListeners();
    }
    
    private void initBotoes() {
        ListaImagemView view = presenter.getView();
        
        view.getButtonCompartilhar().setVisible(false);
        view.getButtonExcluir().setVisible(false);
        view.getButtonVisualizar().setVisible(false);
        view.getButtonGerenciarPermissoes().setVisible(true);
    }
    
    private void initListeners() {
        ListaImagemView view = presenter.getView();
        
        view.getButtonGerenciarPermissoes().addActionListener((ae) -> {
            concederPermissao();
        });
    }
    
    private void concederPermissao() {
        Long idImagemSelecionada = presenter.getIdImagemSelecionada();
        
        int opcao = -1;
        
        if (idImagemSelecionada == null) {
            opcao = JOptionPane.showConfirmDialog(null, "Você não selecionou nenhuma imagem. Deseja continuar?", "", JOptionPane.OK_CANCEL_OPTION);
        }
        
        if (opcao == 0 || idImagemSelecionada != null) {
            new GerenciarPermissaoPresenter(presenter.getContainer(), presenter.getIdUsuarioLogado(), presenter.getUsuarioParaConcederPermissao(), idImagemSelecionada);
        }
    }
}
