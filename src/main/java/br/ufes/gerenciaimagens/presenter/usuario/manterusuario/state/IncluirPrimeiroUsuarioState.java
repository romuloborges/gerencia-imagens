package br.ufes.gerenciaimagens.presenter.usuario.manterusuario.state;

import br.ufes.gerenciaimagens.model.enums.TipoUsuario;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.ManterUsuarioPresenter;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.ManterUsuarioView;
import br.ufes.gerenciaimagens.presenter.login.LoginPresenter;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author rborges
 */
public class IncluirPrimeiroUsuarioState extends ManterUsuarioState {
    
    public IncluirPrimeiroUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        
        initCampos();
        initBotoes();
        initListeners();
    }
    
    private void configuraCloseTela() {
        presenter.getView().addInternalFrameListener(new InternalFrameAdapter() {
            @Override
            public void internalFrameClosing(InternalFrameEvent e) {
                new LoginPresenter(presenter.getPrincipalPresenter(), presenter.getContainer());
            }
        });
    }
    
    private void initCampos() {
        enableCampos();
        
        ManterUsuarioView view = presenter.getView();
        view.getComboTipoUsuario().setSelectedItem(TipoUsuario.ADMINISTRADOR);
        view.getComboTipoUsuario().setEnabled(false);
    }
    
    private void initBotoes() {
        ManterUsuarioView view = presenter.getView();
        
        view.getButtonAcaoState().setText("Salvar");
        view.getButtonAcaoState().setVisible(true);
        view.getButtonFechar().setVisible(true);
        view.getButtonExcluir().setVisible(false);
    }
    
    private void initListeners() {
        ManterUsuarioView view = presenter.getView();
        
        view.getButtonAcaoState().addActionListener((ae) -> {
            salvar();
        });
    }

    @Override
    public void salvar() {
        if (senhasConferem()) {
            try {
                Usuario usuario = getDados();
                usuarioService.insert(usuario);
                JOptionPane.showMessageDialog(null, "Usuário incluído com sucesso", "", JOptionPane.DEFAULT_OPTION);
                fechar();
                new LoginPresenter(presenter.getPrincipalPresenter(), presenter.getContainer());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senhas não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
