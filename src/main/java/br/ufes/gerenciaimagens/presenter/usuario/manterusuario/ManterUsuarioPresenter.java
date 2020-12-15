package br.ufes.gerenciaimagens.presenter.usuario.manterusuario;

import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.presenter.base.BaseInternalFramePresenter;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.state.IncluirPrimeiroUsuarioState;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.state.IncluirUsuarioState;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.state.ManterUsuarioState;
import br.ufes.gerenciaimagens.presenter.usuario.manterusuario.state.VisualizarUsuarioState;
import br.ufes.gerenciaimagens.presenter.usuario.observer.IManterUsuarioObservado;
import br.ufes.gerenciaimagens.presenter.usuario.observer.IManterUsuarioObservador;
import br.ufes.gerenciaimagens.presenter.principal.PrincipalPresenter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDesktopPane;

/**
 *
 * @author rborges
 */
public class ManterUsuarioPresenter extends BaseInternalFramePresenter<ManterUsuarioView> implements IManterUsuarioObservado {
    
    private Usuario usuarioManter;
    private ManterUsuarioState state;
    private PrincipalPresenter principalPresenter;
    private List<IManterUsuarioObservador> observadores;
    
    public ManterUsuarioPresenter(JDesktopPane desktop, PrincipalPresenter principalPresenter) {
        super(desktop, new ManterUsuarioView());
        
        if (principalPresenter == null) {
            throw new RuntimeException("Principal presenter informada é inválida");
        }
        
        this.observadores = new ArrayList<>();
        this.principalPresenter = principalPresenter;
        
        this.usuarioManter = new Usuario();
        this.state = new IncluirPrimeiroUsuarioState(this);
        
        getView().setVisible(true);
    }
    
    public ManterUsuarioPresenter(JDesktopPane desktop, Long idUsuarioLogado) {
        super(desktop, new ManterUsuarioView(), idUsuarioLogado);
        
        this.observadores = new ArrayList<>();
        
        this.usuarioManter = new Usuario();
        this.state = new IncluirUsuarioState(this);
        
        getView().setVisible(true);
    }
    
    public ManterUsuarioPresenter(JDesktopPane desktop, Long idUsuarioLogado, Usuario usuario) {
        super(desktop, new ManterUsuarioView(), idUsuarioLogado);
        if (usuario == null) {
            throw new RuntimeException("Usuário fornecido é inválido");
        }
        
        this.observadores = new ArrayList<>();
        
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

    public PrincipalPresenter getPrincipalPresenter() {
        return principalPresenter;
    }

    @Override
    public void attachObserver(IManterUsuarioObservador observador) {
        observadores.add(observador);
    }

    @Override
    public void detachObserver(IManterUsuarioObservador observador) {
        observadores.remove(observador);
    }

    @Override
    public void notifyObservers() {
        for(IManterUsuarioObservador observador : observadores) {
            observador.update();
        }
    }
    
}
