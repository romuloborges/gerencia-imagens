package br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.state;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioPresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioView;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class EditarUsuarioState extends ManterUsuarioState {
    
    public EditarUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        
        initCampos();
        initBotoes();
        initListeners();
        setDados(presenter.getUsuarioManter());
    }
    
    private void initCampos() {
        ManterUsuarioView view = presenter.getView();
        
        enableCampos();
        
        view.getTextLogin().setEditable(false);
        view.getComboTipoUsuario().setEnabled(false);
    }
    
    private void initBotoes() {
        ManterUsuarioView view = presenter.getView();
        
        view.getButtonAcaoState().setText("Salvar");
        view.getButtonAcaoState().setVisible(true);
        view.getButtonFechar().setVisible(true);
    }
    
    private void initListeners() {
        ManterUsuarioView view = presenter.getView();
        
        view.getButtonAcaoState().addActionListener((ae) -> {
            salvar();
        });
        
        view.getButtonExcluir().addActionListener((ae) -> {
            excluir();
        });
    }
    
    @Override
    public void salvar() {
        if (senhasConferem()) {
            try {
                Usuario usuario = getDados();
                usuarioService.update(usuario);
                JOptionPane.showMessageDialog(null, "Usuário salvo com sucesso", "", JOptionPane.DEFAULT_OPTION);
                fechar();
                presenter.notifyObservers();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senhas não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
