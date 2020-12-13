package br.ufes.gerenciaimagens.presenter.listausuario.manterusuario;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.state.IncluirUsuarioState;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.state.ManterUsuarioState;
import br.ufes.gerenciaimagens.presenter.listausuario.manterusuario.state.VisualizarUsuarioState;
import javax.swing.JDesktopPane;

/**
 *
 * @author rborges
 */
public class ManterUsuarioPresenter extends BaseInternalFramePresenter<ManterUsuarioView> {
    
    private Usuario usuarioManter;
    private ManterUsuarioState state;
    
    public ManterUsuarioPresenter(JDesktopPane desktop, Long idUsuarioLogado) {
        super(desktop, new ManterUsuarioView(), idUsuarioLogado);
        
        this.usuarioManter = new Usuario();
        this.state = new IncluirUsuarioState(this);
        
        getView().setVisible(true);
    }
    
    public ManterUsuarioPresenter(JDesktopPane desktop, Long idUsuarioLogado, Usuario usuario) {
        super(desktop, new ManterUsuarioView(), idUsuarioLogado);
        if (usuario == null) {
            throw new RuntimeException("Usuário fornecido é inválido");
        }
        
        this.usuarioManter = usuario;
        this.state = new VisualizarUsuarioState(this);
        
        getView().setVisible(true);
    }

    public Usuario getUsuarioManter() {
        return usuarioManter;
    }

    public ManterUsuarioState getState() {
        return state;
    }

    public void setState(ManterUsuarioState state) {
        this.state = state;
    }
    
}
