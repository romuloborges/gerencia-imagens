package br.ufes.gerenciaimagens.presenter.imagem.visualiza.state;

import br.ufes.gerenciaimagens.presenter.imagem.visualiza.VisualizaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.VisualizaImagemView;
import br.ufes.gerenciaimagens.service.PermissaoService;
import javax.swing.JOptionPane;

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
        VisualizaImagemView view = presenter.getView();
        
        view.getLabelMensagemNaoPossuiAcesso().setVisible(false);
        view.getButtonSolicitarAcesso().setVisible(false);
        view.getCheckboxSomenteNesta().setVisible(false);
        view.getCheckboxTodasImagens().setVisible(false);
        
        try {
            view.getLabelImagem().setIcon(presenter.getImagem().getImageIcon());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir imagem", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void solicitarAcesso() {
        
    }
    
}
