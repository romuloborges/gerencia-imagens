package br.ufes.gerenciaimagens.presenter.imagem.visualiza.state;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.VisualizaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.imagem.visualiza.VisualizaImagemView;
import br.ufes.gerenciaimagens.service.NotificacaoService;
import br.ufes.gerenciaimagens.service.UsuarioService;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class SolicitaPermissaoVisualizarState extends VisualizaImagemPresenterState {
    
    private NotificacaoService notificacaoService;
    private UsuarioService usuarioService;

    public SolicitaPermissaoVisualizarState(VisualizaImagemPresenter presenter) {
        super(presenter);
        
        initComponents();
        initListeners();
        
        notificacaoService = new NotificacaoService();
        usuarioService = new UsuarioService();
    }
    
    @Override
    protected void initComponents() {
        VisualizaImagemView view = presenter.getView();
        
        view.getLabelMensagemNaoPossuiAcesso().setVisible(true);
        view.getLabelImagem().setVisible(false);
        view.getButtonSolicitarAcesso().setVisible(true);
        view.getCheckboxSomenteNesta().setVisible(true);
        view.getCheckboxTodasImagens().setVisible(true);
    }
    
    private void initListeners() {
        VisualizaImagemView view = presenter.getView();
        
        view.getButtonSolicitarAcesso().addActionListener((ae) -> {
            solicitarAcesso();
        });
    }

    @Override
    public void solicitarAcesso() {
        VisualizaImagemView view = presenter.getView();
        
        if (!view.getCheckboxSomenteNesta().isSelected() && !view.getCheckboxTodasImagens().isSelected()) {
            JOptionPane.showMessageDialog(null, "Marque ao menos uma opção", "", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Long idImagem = null;
                
                if (view.getCheckboxSomenteNesta().isSelected() && !view.getCheckboxTodasImagens().isSelected()) {
                    if (presenter.getImagem() != null) {
                        idImagem = presenter.getImagem().getId();
                        Usuario usuarioLogado = usuarioService.getById(presenter.getIdUsuarioLogado());
                        notificacaoService.enviarNotificacaoParaAdministradores(usuarioLogado.getNome() + " solicitou acesso de visualização na imagem", presenter.getIdUsuarioLogado(), idImagem, TipoNotificacao.SOLICITACAO_VISUALIZACAO);
                        JOptionPane.showMessageDialog(null, "Sua solicitação foi enviada e os administradores foram notificados", "", JOptionPane.DEFAULT_OPTION);
                    }
                } else if (!view.getCheckboxSomenteNesta().isSelected() && view.getCheckboxTodasImagens().isSelected()) {
                    notificacaoService.enviarNotificacaoParaAdministradores("Uma mensagem nova", presenter.getIdUsuarioLogado(), idImagem, TipoNotificacao.SOLICITACAO_VISUALIZACAO);
                    JOptionPane.showMessageDialog(null, "Sua solicitação foi enviada e os administradores foram notificados", "", JOptionPane.DEFAULT_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione apenas uma opção", "", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        fechar();
    }
    
}
