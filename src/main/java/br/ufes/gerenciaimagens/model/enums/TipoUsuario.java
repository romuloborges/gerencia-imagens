package br.ufes.gerenciaimagens.model.enums;

/**
 *
 * @author rborges
 */
public enum TipoUsuario {
    
    NORMAL("Normal"),
    ADMINISTRADOR("Administrador");
    
    private final String descricao;
        
    private TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
    
}
