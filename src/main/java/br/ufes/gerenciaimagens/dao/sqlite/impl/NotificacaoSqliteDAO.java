package br.ufes.gerenciaimagens.dao.sqlite.impl;

import br.ufes.gerenciaimagens.dao.interfaces.INotificacaoDAO;
import br.ufes.gerenciaimagens.dao.manager.SqliteManager;
import br.ufes.gerenciaimagens.model.Imagem;
import br.ufes.gerenciaimagens.model.Notificacao;
import br.ufes.gerenciaimagens.model.Usuario;
import br.ufes.gerenciaimagens.model.enums.TipoNotificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rborges
 */
public class NotificacaoSqliteDAO implements INotificacaoDAO {
    
    private SqliteManager manager;

    public NotificacaoSqliteDAO() {
        this.manager = new SqliteManager();
    }

    @Override
    public List<Notificacao> obterTodasNotificacoes(Long idUsuarioDestinatario) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            String SQL = "SELECT n.id, n.mensagem, n.visualizada, n.data_enviada, remetente.id, remetente.nome, destinatario.id, destinatario.nome, i.id, i.caminho FROM Notificacao n INNER JOIN Usuario remetente ON remetente.id = n.enviada_por INNER JOIN Usuario destinatario ON destinatario.id = n.enviada_para LEFT JOIN Imagem i ON i.id = n.id_imagem WHERE n.enviada_para = ?;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuarioDestinatario);
            
            ResultSet rs = ps.executeQuery();
            
            List<Notificacao> notificacoes = new ArrayList<>();
            
            while(rs.next()) {
                Notificacao notificacao = new Notificacao();
                
                notificacao.setId(rs.getLong(1));
                notificacao.setMensagem(rs.getString(2));
                notificacao.setVisualizada(rs.getBoolean(3));
                String dataEnviada = rs.getString(4);
                
                if (dataEnviada != null && !dataEnviada.isBlank()) {
                    notificacao.setDataEnviada(LocalDateTime.parse(dataEnviada));
                }
                
                Usuario remetente = new Usuario();
                remetente.setId(rs.getLong(5));
                remetente.setNome(rs.getString(6));
                
                notificacao.setRemetente(remetente);
                
                Usuario destinatario = new Usuario();
                destinatario.setId(rs.getLong(7));
                destinatario.setNome(rs.getString(8));
                
                notificacao.setDestinatario(destinatario);
               
                Long idImagem = rs.getLong(9);
                
                if (idImagem != null) {
                    Imagem imagem = new Imagem();
                    
                    imagem.setId(idImagem);
                    imagem.setCaminho(rs.getString(10));
                    
                    notificacao.setImagem(imagem);
                }
                
                notificacoes.add(notificacao);
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return notificacoes;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao ler notificações");
        }
    }

    @Override
    public Long contaNotificacoesNaoLidas(Long idUsuarioDestinatario) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            String SQL = "SELECT COUNT(*) FROM Notificacao n INNER JOIN Usuario destinatario ON destinatario.id = n.enviada_para WHERE destinatario.id = ? AND visualizada = 0;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuarioDestinatario);
            
            ResultSet rs = ps.executeQuery();
            
            List<Notificacao> notificacoes = new ArrayList<>();
            
            Long quantidadeNotificacoesNaoLidas = 0L;
            
            while(rs.next()) {
                quantidadeNotificacoesNaoLidas = rs.getLong(1);
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return quantidadeNotificacoesNaoLidas;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao contar notificações");
        }
    }
    
    @Override
    public void enviarNotificacaoParaAdministradores(String mensagem, Long idUsuarioRemetente, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            StringBuilder sql = new StringBuilder();
            
            sql.append( " INSERT " );
            sql.append( " 	INTO " );
            sql.append( " 	Notificacao(mensagem, enviada_por, enviada_para, tipo, data_enviada, id_imagem) " );
            sql.append( " SELECT " );
            sql.append( " 	?, " );
            sql.append( " 	?, " );
            sql.append( " 	id, " );
            sql.append( " 	?, " );
            sql.append( " 	?, " );
            if (idImagem == null) {
                sql.append( " 	NULL " );
            } else {
                sql.append( " 	? " );
            }
            sql.append( " FROM " );
            sql.append( " 	Usuario " );
            sql.append( " WHERE " );
            sql.append( " 	excluido = 0 " );
            sql.append( " 	AND tipo = 'ADMINISTRADOR'; " );

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql.toString());
            
            int parametroAtual = 1;
            
            ps.setString(1, mensagem);
            ps.setLong(2, idUsuarioRemetente);
            ps.setString(3, tipoNotificacao.name());
            ps.setString(4, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            
            if (idImagem != null) {
                ps.setLong(5, idImagem);
            }
            
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao enviar");
        }
    }

    @Override
    public void enviarNotificacao(String mensagem, Long idUsuarioRemetente, Long idUsuarioDestinatario, Long idImagem, TipoNotificacao tipoNotificacao) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            StringBuilder sql = new StringBuilder();
            
            sql.append( " INSERT " );
            sql.append( " 	INTO " );
            sql.append( " 	Notificacao(mensagem, enviada_por, enviada_para, tipo, data_enviada, id_imagem) " );
            sql.append( " VALUES(?, ?, ?, ?, ? " );
            if (idImagem != null) {
                sql.append(", ? ");
            }
            sql.append(");");
            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql.toString());
            
            int parametroAtual = 1;
            
            ps.setString(1, mensagem);
            ps.setLong(2, idUsuarioRemetente);
            ps.setLong(3, idUsuarioDestinatario);
            ps.setString(3, tipoNotificacao.name());
            ps.setString(4, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
            
            if (idImagem != null) {
                ps.setLong(5, idImagem);
            }
            
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao enviar");
        }
    }

    @Override
    public void marcarNotificacoesComoLidas(Long idUsuarioDestinatario) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            String SQL = "UPDATE Notificacao SET visualizada = 1 WHERE enviada_para = ?;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setLong(1, idUsuarioDestinatario);
            
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao atualizar");
        }
    }
    
}
