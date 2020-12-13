package br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.state;

import br.ufes.gerenciaimagens.model.TipoUsuario;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.criptografasenhautil.CriptografaSenhaUtil;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioPresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioView;
import br.ufes.gerenciaimagens.service.UsuarioService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public abstract class ManterUsuarioState {
    
    protected ManterUsuarioPresenter presenter;
    protected UsuarioService usuarioService;
    
    public ManterUsuarioState(ManterUsuarioPresenter presenter) {
        if (presenter == null) {
            throw new RuntimeException("Presenter não informada");
        }
        
        this.presenter = presenter;
        
        removeListeners();
        initComponents();
        
        this.usuarioService = new UsuarioService();
    }
    
    public void salvar() {
        
    }
    
    public void habilitarEdicao() {
        
    }
    
    public void excluir() {
        try {
            int opcao = JOptionPane.showConfirmDialog(null, "Deseja excluir o usuário?", "", JOptionPane.YES_NO_OPTION);
            
            if (opcao == 0) {
                Usuario usuario = presenter.getUsuarioManter();
                usuarioService.delete(usuario.getId());
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro ao excluir", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void fechar() {
        presenter.getView().setVisible(false);
        presenter.getView().dispose();
    }
    
    private void initComponents() {
        JComboBox<TipoUsuario> comboTipoUsuario = presenter.getView().getComboTipoUsuario();
        
        for(TipoUsuario tipo : TipoUsuario.values()) {
            comboTipoUsuario.removeItem(tipo);
        }
        
        for(TipoUsuario tipo : TipoUsuario.values()) {
            comboTipoUsuario.addItem(tipo);
        }
        
        comboTipoUsuario.setSelectedIndex(0);
    }
    
    private void removeListeners() {
        removerActionListeners(presenter.getView().getButtonFechar());
        removerActionListeners(presenter.getView().getButtonAcaoState());
    }
    
    protected Usuario getDados() {
        ManterUsuarioView view = presenter.getView();
        Usuario usuario = presenter.getUsuarioManter();
        
        usuario.setNome(view.getTextNome().getText());
        usuario.setLogin(view.getTextLogin().getText());
        if (view.getComboTipoUsuario().getSelectedItem() != null) {
            usuario.setTipo((TipoUsuario) view.getComboTipoUsuario().getSelectedItem());
        }
        
        try {
            usuario.setSenha(CriptografaSenhaUtil.getInstancia().criptografar(new String(view.getTextSenha().getPassword())));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter dados do usuário", "", JOptionPane.ERROR_MESSAGE);
        }
        
        return usuario;
    }

    protected void setDados(Usuario usuario) {
        ManterUsuarioView view = presenter.getView();
        
        view.getTextNome().setText(usuario.getNome());
        view.getTextLogin().setText(usuario.getLogin());
        view.getComboTipoUsuario().setSelectedItem(usuario.getTipo());
        view.getButtonAcaoState().setVisible(true);
        view.getButtonAcaoState().setEnabled(true);
    }

    protected void disableCampos() {
        ManterUsuarioView view = presenter.getView();
        
        view.getTextNome().setEditable(false);
        view.getTextLogin().setEditable(false);
        view.getTextSenha().setEditable(false);
        view.getTextSenhaConferencia().setEditable(false);
        view.getComboTipoUsuario().setEnabled(false);
        view.getButtonAcaoState().setVisible(false);
        view.getButtonAcaoState().setEnabled(false);
    }

    protected void enableCampos() {
        ManterUsuarioView view = presenter.getView();
        
        view.getTextNome().setEditable(true);
        view.getTextLogin().setEditable(true);
        view.getTextSenha().setEditable(true);
        view.getTextSenhaConferencia().setEditable(true);
        view.getComboTipoUsuario().setEnabled(true);
        view.getButtonAcaoState().setVisible(true);
        view.getButtonAcaoState().setEnabled(true);
    }

    protected boolean senhasConferem() {
        String senha = new String(presenter.getView().getTextSenha().getPassword());
        String senhaNovamente = new String(presenter.getView().getTextSenhaConferencia().getPassword());
        
        return senha.equals(senhaNovamente);
    }

    private void removerActionListeners(JButton btn) {
        for (var action : btn.getActionListeners()) {
            btn.removeActionListener(action);
        }
    }
    
}
