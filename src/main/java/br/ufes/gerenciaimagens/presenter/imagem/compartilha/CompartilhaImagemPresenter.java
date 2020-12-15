package br.ufes.gerenciaimagens.presenter.imagem.compartilha;

import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.service.NotificacaoService;
import br.ufes.gerenciaimagens.service.UsuarioService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rborges
 */
public class CompartilhaImagemPresenter extends BaseInternalFramePresenter<CompartilhaImagemView> {
    
    private NotificacaoService notificacaoService;
    private UsuarioService usuarioService;
    private List<Usuario> usuarios;
    private Imagem imagem;
    
    public CompartilhaImagemPresenter(JDesktopPane desktop, Long idUsuarioLogado, Imagem imagem) {
        super(desktop, new CompartilhaImagemView(), idUsuarioLogado);

        notificacaoService = new NotificacaoService();
        this.usuarioService = new UsuarioService();
        this.imagem = imagem;
        removeListeners();
        initListeners();
        configuraTabelaUsuarios();
        usuarios = new ArrayList<>();
        buscarUsuarios();

        getView().setVisible(true);
    }
    
    private void configuraTabelaUsuarios() {
        JTable tabelaUsuarios = getView().getTableUsuarios();
        DefaultTableModel tmUsuarios = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tmUsuarios.setDataVector(new Object[][]{}, new String[]{"Usuário"});
        
        tabelaUsuarios.setModel(tmUsuarios);
        
        tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void removeListeners() {
        removerActionListeners(getView().getButtonCompartilhar());
    }
    
    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
    
    private void initListeners() {
        getView().getButtonCompartilhar().addActionListener((ae) -> {
            compartilhar();
        });
    }
    
    private void buscarUsuarios() {
        try {
            DefaultTableModel tmUsuarios = (DefaultTableModel) getView().getTableUsuarios().getModel();

            tmUsuarios.setNumRows(0);

            this.usuarios = usuarioService.filter(null);

            for(Usuario usuario : usuarios) {
                tmUsuarios.addRow(new String[]{ usuario.getNome() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void compartilhar() {
        int usuarioSelecionado = getView().getTableUsuarios().getSelectedRow();
        if (usuarioSelecionado >= 0) {
            try {
                Usuario usuarioDestino = usuarios.get(usuarioSelecionado);
                Usuario usuarioLogado = usuarioService.getById(getIdUsuarioLogado());
                
                notificacaoService.enviarNotificacao("O usuário " + usuarioLogado.getNome() + " compartilhou uma imagem com você", getIdUsuarioLogado(), usuarioDestino.getId(), imagem.getId(), TipoNotificacao.COMPARTILHAMENTO);
                JOptionPane.showMessageDialog(null, "Compartilhamento feito com sucesso");
                getView().dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
