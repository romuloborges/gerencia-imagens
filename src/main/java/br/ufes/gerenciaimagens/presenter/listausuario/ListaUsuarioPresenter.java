package br.ufes.gerenciaimagens.presenter.listausuario;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.service.UsuarioService;
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
public class ListaUsuarioPresenter extends BaseInternalFramePresenter<ListaUsuarioView> {
    
    private UsuarioService usuarioService;
    private List<Usuario> usuarios;
    
    public ListaUsuarioPresenter(JDesktopPane desktop, Long idUsuarioLogado) {
        super(desktop, new ListaUsuarioView(), idUsuarioLogado);
        
        removeListeners();
        initListeners();
        configuraTabelaUsuarios();
        
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
        removerActionListeners(getView().getButtonEditar());
        removerActionListeners(getView().getButtonDefinirPermissoes());
        removerActionListeners(getView().getButtonExcluir());
        
    }
    
    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
    
    private void initListeners() {
        getView().getButtonBuscar().addActionListener((ae) -> {
            try {
                buscarUsuarios();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        getView().getButtonNovo().addActionListener((ae) -> {
            
        });
        
        getView().getButtonEditar().addActionListener((ae) -> {
            
        });
        
        getView().getButtonExcluir().addActionListener((ae) -> {
            
        });
        
        getView().getButtonDefinirPermissoes().addActionListener((ae) -> {
            
        });
    }
    
    private void buscarUsuarios() throws Exception {
        String nome = getView().getTextNome().getText();
        
        DefaultTableModel tmUsuarios = (DefaultTableModel) getView().getTableUsuarios().getModel();
        
        tmUsuarios.setNumRows(0);
        
        this.usuarios = usuarioService.filter(nome);
        
        for(Usuario usuario : usuarios) {
            tmUsuarios.addRow(new String[]{ usuario.getNome(), usuario.getLogin(), usuario.getDescricaoTipo() });
        }
    }
    
}
