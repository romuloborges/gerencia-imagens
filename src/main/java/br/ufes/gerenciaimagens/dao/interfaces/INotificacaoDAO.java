package br.ufes.gerenciaimagens.dao.interfaces;

import br.ufes.gerenciaimagens.model.Notificacao;
import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;
import java.util.List;

/**
 *
 * @author rborges
 */
public interface INotificacaoDAO {
    
    public List<Notificacao> obterTodasNotificacoes(Long idUsuarioDestinatario) throws Exception;
    public Long contaNotificacoesNaoLidas(Long idUsuarioDestinatario) throws Exception;
    public void enviarNotificacaoParaAdministradores(String mensagem, Long idUsuarioRemetente, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception;
    
}
