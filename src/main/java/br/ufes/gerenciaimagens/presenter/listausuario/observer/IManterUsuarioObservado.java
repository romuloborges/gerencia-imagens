package br.ufes.gerenciaimagens.presenter.listausuario.observer;

/**
 *
 * @author rborges
 */
public interface IManterUsuarioObservado {
    
    public void attachObserver(IManterUsuarioObservador observador);
    public void detachObserver(IManterUsuarioObservador observador);
    public void notifyObservers();
    
}
