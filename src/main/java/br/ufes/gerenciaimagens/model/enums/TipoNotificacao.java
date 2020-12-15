package br.ufes.gerenciaimagens.model.enums;

/**
 *
 * @author rborges
 */
public enum TipoNotificacao {
    
    SOLICITACAO_VISUALIZACAO("Solicitação de Visualização"),
    SOLICITACAO_EXCLUSAO("Solicitação de Exclusão"),
    SOLICITACAO_COMPARTILHAMENTO("Solicitação de Compartilhamento"),
    COMPARTILHAMENTO("Compartilhamento");
    
    private final String descricao;
    
    private TipoNotificacao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
}
