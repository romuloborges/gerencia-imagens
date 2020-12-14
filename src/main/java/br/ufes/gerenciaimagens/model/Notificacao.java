package br.ufes.gerenciaimagens.model;

import java.time.LocalDateTime;

/**
 *
 * @author rborges
 */
public class Notificacao {
    
    private Long id;
    private String mensagem;
    private Usuario remetente;
    private Usuario destinatario;
    private boolean visualizada;
    private Imagem imagem;
    private LocalDateTime dataEnviada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public boolean isVisualizada() {
        return visualizada;
    }

    public void setVisualizada(boolean visualizada) {
        this.visualizada = visualizada;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public LocalDateTime getDataEnviada() {
        return dataEnviada;
    }

    public void setDataEnviada(LocalDateTime dataEnviada) {
        this.dataEnviada = dataEnviada;
    }
    
}
