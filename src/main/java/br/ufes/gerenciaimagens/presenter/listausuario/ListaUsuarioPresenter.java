package br.ufes.gerenciaimagens.presenter.listausuario;

import br.ufes.gerenciaimagens.model.enums.TipoUsuario;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.listaimagem.ListaImagemPresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioPresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.observer.IManterUsuarioObservador;
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
public class ListaUsuarioPresenter extends BaseInternalFramePresenter<ListaUsuarioView> implements IManterUsuarioObservador {
    
    private UsuarioService usuarioService;
    private List<Usuario> usuarios;
    
    public ListaUsuarioPresenter(JDesktopPane desktop, Long idUsuarioLogado) {
        super(desktop, new ListaUsuarioView(), idUsuarioLogado);
        usuarioService = new UsuarioService();
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
        
        tmUsuarios.setDataVector(new Object[][]{}, new String[]{"Usuário", "Login", "Tipo"});
        
        tabelaUsuarios.setModel(tmUsuarios);
        
        tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private void removeListeners() {
        removerActionListeners(getView().getButtonBuscar());
        removerActionListeners(getView().getButtonNovo());
        removerActionListeners(getView().getButtonVisualizar());
        removerActionListeners(getView().getButtonDefinirPermissoes());
    }
    
    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
    
    private void initListeners() {
        getView().getButtonBuscar().addActionListener((ae) -> {
            buscarUsuarios();
        });
        
        getView().getButtonNovo().addActionListener((ae) -> {
            ManterUsuarioPresenter manterUsuarioPresenter = new ManterUsuarioPresenter(getContainer(), getIdUsuarioLogado());
            manterUsuarioPresenter.attachObserver(this);
        });
        
        getView().getButtonVisualizar().addActionListener((ae) -> {
            visualizar();
        });
        
        getView().getButtonDefinirPermissoes().addActionListener((ae) -> {
            concederPermissoes();
        });
    }
    
    private void buscarUsuarios() {
        try { 
            String nome = getView().getTextNome().getText();

            DefaultTableModel tmUsuarios = (DefaultTableModel) getView().getTableUsuarios().getModel();

            tmUsuarios.setNumRows(0);

            this.usuarios = usuarioService.filter(nome);

            for(Usuario usuario : usuarios) {
                tmUsuarios.addRow(new String[]{ usuario.getNome(), usuario.getLogin(), usuario.getDescricaoTipo() });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void visualizar() {
        int linhaSelecionada = getView().getTableUsuarios().getSelectedRow();
        if (linhaSelecionada >= 0) {
            Usuario usuario = usuarios.get(linhaSelecionada);
            ManterUsuarioPresenter manterUsuarioPresenter = new ManterUsuarioPresenter(getContainer(), getIdUsuarioLogado(), usuario);
            manterUsuarioPresenter.attachObserver(this);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para visualizar", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void update() {
        buscarUsuarios();
    }
    
    private void concederPermissoes() {
        int linhaSelecionada = getView().getTableUsuarios().getSelectedRow();
        if (linhaSelecionada >= 0) {
            Usuario usuario = usuarios.get(linhaSelecionada);
            if (!TipoUsuario.ADMINISTRADOR.equals(usuario.getTipo())) {
                new ListaImagemPresenter(getContainer(), getIdUsuarioLogado(), usuario);
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível editar as permissões de um administrador", "", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um usuário para conceder permissão", "", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
