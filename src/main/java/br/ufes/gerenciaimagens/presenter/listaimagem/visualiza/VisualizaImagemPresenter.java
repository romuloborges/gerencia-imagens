package br.ufes.gerenciaimagens.presenter.listaimagem.visualiza;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.TipoPermissao;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.state.SolicitaPermissaoVisualizarState;
import br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.state.VisualizaImagemComPermissaoState;
import br.ufes.gerenciaimagens.presenter.listaimagem.visualiza.state.VisualizaImagemPresenterState;
import br.ufes.gerenciaimagens.service.PermissaoService;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        
        try {
            boolean possuiPermissao = permissaoService.possuiPermissao(idUsuarioLogado, imagem.getId(), TipoPermissao.VISUALIZACAO);
            if (possuiPermissao) {
                this.state = new VisualizaImagemComPermissaoState(this);
            } else {
                this.state = new SolicitaPermissaoVisualizarState(this);
            }
        } catch (Exception ex) {
            Logger.getLogger(VisualizaImagemPresenter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
