package br.ufes.gerenciaimagens.service;

import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;
import br.ufes.gerenciaimagens.repository.NotificacaoRepository;

/**
 *
 * @author rborges
 */
public class NotificacaoService {
    
    private NotificacaoRepository notificacaoRepository;
    
    public NotificacaoService() {
        this.notificacaoRepository = new NotificacaoRepository();
    }
    
    public void enviarNotificacaoParaAdministradores(String mensagem, Long idUsuarioRemetente, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception {
        notificacaoRepository.enviarNotificacaoParaAdministradores(mensagem, idUsuarioRemetente, idImagem, tipoNotificacao);
    }
    
}
