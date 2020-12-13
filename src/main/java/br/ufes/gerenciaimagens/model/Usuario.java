package br.ufes.gerenciaimagens.model;

/**
 *
 * @author rborges
 */
public class Usuario {
    
    private Long id;
    private String login;
    private String senha;
    private String nome;
    private TipoUsuario tipo;

    public Usuario() {
    }

    public Usuario(String login, String senha, String nome, TipoUsuario tipo) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Usuario(Long id, String login, String senha, String nome, TipoUsuario tipo) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
    }
    
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricaoTipo() {
        return tipo.getDescricao();
    }
    
}
