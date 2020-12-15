package br.ufes.gerenciaimagens.presenter.gerenciarpermissao;

import br.ufes.gerenciaimagens.model.enums.TipoPermissao;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.service.PermissaoService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class GerenciarPermissaoPresenter extends BaseInternalFramePresenter<GerenciarPermissaoView> {
    
    private PermissaoService permissaoService;
    private Usuario usuarioParaConcederPermissao;
    private Long idImagem;
    
    public GerenciarPermissaoPresenter(JDesktopPane desktop, Long idUsuarioLogado, Usuario usuarioParaConcederPermissao, Long idImagem) {
        super(desktop, new GerenciarPermissaoView(), idUsuarioLogado);
        
        this.permissaoService = new PermissaoService();
        this.usuarioParaConcederPermissao = usuarioParaConcederPermissao;
        this.idImagem = idImagem;
        
        initCheckboxes();
        initListeners();
        
        getView().setVisible(true);
    }
    
    private void initListeners() {
        getView().getButtonSalvar().addActionListener((ae) -> {
            salvarPermissoes();
        });
    }
    
    private void salvarPermissoes() {
        try {
            permissaoService.salvarPermissoes(montaListaPermissoesSelecionadas(), usuarioParaConcederPermissao.getId(), idImagem);
            JOptionPane.showMessageDialog(null, "Permissões salvas com sucesso", "Sucesso", JOptionPane.DEFAULT_OPTION);
            fechar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private List<TipoPermissao> montaListaPermissoesSelecionadas() {
        List<TipoPermissao> permissoes = new ArrayList<>();
        
        if (getView().getCheckboxCompartilhar().isSelected()) {
            permissoes.add(TipoPermissao.COMPARTILHAMENTO);
        } 
        
        if (getView().getCheckboxExcluir().isSelected()) {
            permissoes.add(TipoPermissao.EXCLUSAO);
        }
        
        if (getView().getCheckboxVisualizar().isSelected()) {
            permissoes.add(TipoPermissao.VISUALIZACAO);
        }
        
        return permissoes;
    }
    
    private void initCheckboxes() {
        try {
            boolean permissaoCompartilhar = false;
            boolean permissaoVisualizar = false;
            boolean permissaoExcluir = false;
            
            if (idImagem == null) {
                permissaoCompartilhar = permissaoService.possuiPermissaoEmTodasImagens(usuarioParaConcederPermissao.getId(), TipoPermissao.COMPARTILHAMENTO);
                permissaoVisualizar = permissaoService.possuiPermissaoEmTodasImagens(usuarioParaConcederPermissao.getId(), TipoPermissao.VISUALIZACAO); 
                permissaoExcluir = permissaoService.possuiPermissaoEmTodasImagens(usuarioParaConcederPermissao.getId(), TipoPermissao.EXCLUSAO);
            } else {
                permissaoCompartilhar = permissaoService.possuiPermissao(usuarioParaConcederPermissao.getId(), idImagem, TipoPermissao.COMPARTILHAMENTO);
                permissaoVisualizar = permissaoService.possuiPermissao(usuarioParaConcederPermissao.getId(), idImagem, TipoPermissao.VISUALIZACAO);
                permissaoExcluir = permissaoService.possuiPermissao(usuarioParaConcederPermissao.getId(), idImagem, TipoPermissao.EXCLUSAO);
            }
            
            getView().getCheckboxCompartilhar().setSelected(permissaoCompartilhar);
            getView().getCheckboxVisualizar().setSelected(permissaoVisualizar);
            getView().getCheckboxExcluir().setSelected(permissaoExcluir);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao carregar permissões", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void fechar() {
        getView().dispose();
    }
    
}