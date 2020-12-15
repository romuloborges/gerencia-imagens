package br.ufes.gerenciaimagens.presenter.imagem.state;

import br.ufes.gerenciaimagens.memento.MementoImagem;
import br.ufes.gerenciaimagens.memento.Zelador;
import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.presenter.imagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.imagem.ListaImagemView;
import br.ufes.gerenciaimagens.presenter.imagem.compartilha.CompartilhaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.VisualizaImagemPresenter;
import br.ufes.gerenciaimagens.service.ImagemService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class ListagemImagensState extends ListaImagemState {
    
    private ImagemService imagemService;
    
    public ListagemImagensState(ListaImagemPresenter presenter) {
        super(presenter);
        
        initBotoes();
        initListeners();
        
        imagemService = new ImagemService();
    }
    
    private void initBotoes() {
        ListaImagemView view = presenter.getView();
        
        view.getButtonCompartilhar().setVisible(true);
        view.getButtonExcluir().setVisible(true);
        view.getButtonVisualizar().setVisible(true);
        view.getButtonGerenciarPermissoes().setVisible(false);
        view.getButtonDesfazerExclusao().setVisible(true);
    }
    
    private void initListeners() {
        ListaImagemView view = presenter.getView();
        
        view.getButtonCompartilhar().addActionListener((ae) -> {
            compartilhar();
        });
        
        view.getButtonExcluir().addActionListener((ae) -> {
            excluir();
        });
        
        view.getButtonVisualizar().addActionListener((ae) -> {
            visualizar();
        });
        
        view.getButtonDesfazerExclusao().addActionListener((ae) -> {
            desfazerExclusao();
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

    @Override
    public void excluir() {
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
                try {
                    Zelador.getInstancia().add(imagem.criar());
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "", JOptionPane.OK_CANCEL_OPTION);
                    
                    if (opcao == 0) {
                        imagemService.excluir(imagem.getId());
                        JOptionPane.showMessageDialog(null, "Imagem excluída com sucesso", "", JOptionPane.DEFAULT_OPTION);
                        presenter.buscarImagens();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma imagem", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void compartilhar() {
        new CompartilhaImagemPresenter(presenter.getContainer(), presenter.getIdUsuarioLogado(), presenter.getImagemSelecionada());
    }

    @Override
    public void desfazerExclusao() {
        try {
            MementoImagem ultimo = Zelador.getInstancia().getUltimo();
            if (ultimo != null) {
                Imagem imagem = new Imagem();
                imagem.restaurar(ultimo);
                imagemService.restaurar(imagem.getId());
                presenter.buscarImagens();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}