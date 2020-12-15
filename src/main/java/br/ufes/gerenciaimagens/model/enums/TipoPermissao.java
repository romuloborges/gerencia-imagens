package br.ufes.gerenciaimagens.model.enums;

/**
 *
 * @author rborges
 */
public enum TipoPermissao {
    
    VISUALIZACAO("Visualização"),
    EXCLUSAO("Exclusão"),
    COMPARTILHAMENTO("Compartilhamento");
    
    private final String descricao;
    
    private TipoPermissao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
}
