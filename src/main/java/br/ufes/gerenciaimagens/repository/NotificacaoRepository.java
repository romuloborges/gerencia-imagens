package br.ufes.gerenciaimagens.repository;

import br.ufes.gerenciaimagens.dao.collection.NotificacaoDAOCollection;
import br.ufes.gerenciaimagens.dao.interfaces.INotificacaoDAO;
import br.ufes.gerenciaimagens.model.Notificacao;
import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;
import java.util.List;

/**
 *
 * @author rborges
 */
public class NotificacaoRepository {
    
    private INotificacaoDAO notificacaoDAO;
    
    public NotificacaoRepository() {
        notificacaoDAO = NotificacaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
    }
    
    public Long contaNotificacoesNaoLidas(Long idUsuarioDestinatario) throws Exception {
        return notificacaoDAO.contaNotificacoesNaoLidas(idUsuarioDestinatario);
    }
    
    public List<Notificacao> obterTodasNotificacoes(Long idUsuarioDestinatario) throws Exception {
        return notificacaoDAO.obterTodasNotificacoes(idUsuarioDestinatario);
    }
    
    public void enviarNotificacaoParaAdministradores(String mensagem, Long idUsuarioRemetente, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception {
        if (mensagem == null || mensagem.isBlank()) {
            throw new Exception("Mensagem não informada");
        }
        
        if (idUsuarioRemetente == null) {
            throw new Exception("Usuário que está enviando a notificação não pode ser identificado");
        }
        
        if (tipoNotificacao == null) {
            throw new Exception("Tipo da notificação não foi informado");
        }
        
        notificacaoDAO.enviarNotificacaoParaAdministradores(mensagem, idUsuarioRemetente, idImagem, tipoNotificacao);
    }
    
    public void enviarNotificacao(String mensagem, Long idUsuarioRemetente, Long idUsuarioDestinatario, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception {
        if (mensagem == null || mensagem.isBlank()) {
            throw new Exception("Mensagem não informada");
        }
        
        if (idUsuarioRemetente == null) {
            throw new Exception("Usuário que está enviando a notificação não pode ser identificado");
        }
        
        if (idUsuarioDestinatario == null) {
            throw new Exception("Usuário que está recebendo a notificação não pode ser identificado");
        }
        
        if (tipoNotificacao == null) {
            throw new Exception("Tipo da notificação não foi informado");
        }
        
        notificacaoDAO.enviarNotificacao(mensagem, idUsuarioRemetente, idUsuarioDestinatario, idImagem, tipoNotificacao);
    }
    
    public void marcarNotificacoesComoLidas(Long idUsuarioDestinatario) throws Exception {
        if (idUsuarioDestinatario == null) {
            throw new Exception("Usuário destinatário fornecido é inválido");
        }
        
        notificacaoDAO.marcarNotificacoesComoLidas(idUsuarioDestinatario);
    }
    
}
