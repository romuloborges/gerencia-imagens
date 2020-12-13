package br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.state;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioPresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.ManterUsuarioView;
import javax.swing.JOptionPane;

/**
 *
 * @author rborges
 */
public class IncluirUsuarioState extends ManterUsuarioState {
    
    public IncluirUsuarioState(ManterUsuarioPresenter presenter) {
        super(presenter);
        
        initCampos();
        initBotoes();
        initListeners();
    }
    
    private void initCampos() {
        enableCampos();
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
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senhas não conferem", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
