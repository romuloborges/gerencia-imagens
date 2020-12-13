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
    private boolean excluido;

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

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public boolean isExcluido() {
        return excluido;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public void setExcluido(boolean excluido) {
        this.excluido = excluido;
    }
    
}
