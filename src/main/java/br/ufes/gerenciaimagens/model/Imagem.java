package br.ufes.gerenciaimagens.model;

import br.ufes.gerenciaimagens.memento.MementoImagem;
import br.ufes.gerenciaimagens.model.interfaces.IImagem;
import javax.swing.ImageIcon;

/**
 *
 * @author rborges
 */
public class Imagem implements IImagem {
    
    private Long id;
    private String caminho;
    private boolean excluida;

    public Imagem() {
        
    }
    
    public Imagem(Long id, String caminho, boolean excluida) {
        this.id = id;
        this.caminho = caminho;
        this.excluida = excluida;
    }
    
    public MementoImagem criar() {
        return new MementoImagem(id, caminho, excluida);
    }
    
    public void restaurar(MementoImagem memento) {
        this.id = memento.getId();
        this.caminho = memento.getCaminho();
        this.excluida = memento.isExcluida();
    }
    
    @Override
    public ImageIcon getImageIcon() throws Exception {
        return new ImageIcon(getCaminho());
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getCaminho() {
        return caminho;
    }
    
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public boolean isExcluida() {
        return excluida;
    }

    public void setExcluida(boolean excluida) {
        this.excluida = excluida;
    }
    
}
