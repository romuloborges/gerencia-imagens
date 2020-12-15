package br.ufes.gerenciaimagens.presenter.imagem.visualiza;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.enums.TipoPermissao;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.state.SolicitaPermissaoVisualizarState;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.state.VisualizaImagemComPermissaoState;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.state.VisualizaImagemPresenterState;
import br.ufes.gerenciaimagens.service.PermissaoService;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class VisualizaImagemPresenter extends BaseInternalFramePresenter<VisualizaImagemView> {
    
    private PermissaoService permissaoService;
    private Imagem imagem;
    private VisualizaImagemPresenterState state;
    
    public VisualizaImagemPresenter(JDesktopPane desktop, Long idUsuarioLogado, Imagem imagem) {
        super(desktop, new VisualizaImagemView(), idUsuarioLogado);
        
        if (imagem == null) {
            JOptionPane.showMessageDialog(null, "Não foi possível encontrar a imagem", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        this.imagem = imagem;
        
        permissaoService = new PermissaoService();
        iniciaState();
        
        getView().setVisible(true);
    }
    
    private void iniciaState() {
        try {
            boolean possuiPermissao = permissaoService.possuiPermissao(getIdUsuarioLogado(), imagem.getId(), TipoPermissao.VISUALIZACAO);
            if (possuiPermissao) {
                this.state = new VisualizaImagemComPermissaoState(this);
            } else {
                this.state = new SolicitaPermissaoVisualizarState(this);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Imagem getImagem() {
        return imagem;
    }
    
}
