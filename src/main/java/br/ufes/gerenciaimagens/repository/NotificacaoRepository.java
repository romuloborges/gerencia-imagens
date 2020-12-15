package br.ufes.gerenciaimagens.repository;

import br.ufes.gerenciaimagens.dao.collection.NotificacaoDAOCollection;
import br.ufes.gerenciaimagens.dao.interfaces.INotificacaoDAO;
import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;

/**
 *
 * @author rborges
 */
public class NotificacaoRepository {
    
    private INotificacaoDAO notificacaoDAO;
    
    public NotificacaoRepository() {
        notificacaoDAO = NotificacaoDAOCollection.getInstancia().cria(System.getProperty("db.name"));
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
    
}
