package br.ufes.gerenciaimagens.memento;

/**
 *
 * @author rborges
 */
public class MementoImagem {
    
    private Long id;
    private String caminho;
    private boolean excluida;

    public MementoImagem(Long id, String caminho, boolean excluida) {
        this.id = id;
        this.caminho = caminho;
        this.excluida = excluida;
    }

    public Long getId() {
        return id;
    }

    public String getCaminho() {
        return caminho;
    }

    public boolean isExcluida() {
        return excluida;
    }
    
}
