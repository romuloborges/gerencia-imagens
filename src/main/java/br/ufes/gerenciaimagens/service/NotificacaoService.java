package br.ufes.gerenciaimagens.service;

import br.ufes.gerenciaimagens.model.Notificacao;
import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;
import br.ufes.gerenciaimagens.repository.NotificacaoRepository;
import java.util.List;

/**
 *
 * @author rborges
 */
public class NotificacaoService {
    
    private NotificacaoRepository notificacaoRepository;
    
    public NotificacaoService() {
        this.notificacaoRepository = new NotificacaoRepository();
    }
    
    public Long contaNotificacoesNaoLidas(Long idUsuarioDestinatario) throws Exception {
        return notificacaoRepository.contaNotificacoesNaoLidas(idUsuarioDestinatario);
    }
    
    public List<Notificacao> obterTodasNotificacoes(Long idUsuarioDestinatario) throws Exception {
        return notificacaoRepository.obterTodasNotificacoes(idUsuarioDestinatario);
    }
    
    public void enviarNotificacaoParaAdministradores(String mensagem, Long idUsuarioRemetente, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception {
        notificacaoRepository.enviarNotificacaoParaAdministradores(mensagem, idUsuarioRemetente, idImagem, tipoNotificacao);
    }
    
    public void enviarNotificacao(String mensagem, Long idUsuarioRemetente, Long idUsuarioDestinatario, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception {
        notificacaoRepository.enviarNotificacao(mensagem, idUsuarioRemetente, idUsuarioDestinatario, idImagem, tipoNotificacao);
    }
    
    public void marcarNotificacoesComoLidas(Long idUsuarioDestinatario) throws Exception {
        notificacaoRepository.marcarNotificacoesComoLidas(idUsuarioDestinatario);
    }
    
}
