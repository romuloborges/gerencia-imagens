package br.ufes.gerenciaimagens.dao.sqlite.impl;

import br.ufes.gerenciaimagens.dao.interfaces.IUsuarioDAO;
import br.ufes.gerenciaimagens.dao.manager.SqliteManager;
import br.ufes.gerenciaimagens.model.enums.TipoUsuario;
import br.ufes.gerenciaimagens.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rborges
 */
public class UsuarioSqliteDAO implements IUsuarioDAO {
    
    private SqliteManager manager;

    public UsuarioSqliteDAO() {
        this.manager = new SqliteManager();
    }

    @Override
    public void insert(Usuario usuario) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            String SQL = "INSERT INTO Usuario(login, senha, nome, tipo) VALUES(?, ?, ?, ?);";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getLogin());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getNome());
            ps.setString(4, usuario.getTipo().name());
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao inserir");
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            String SQL = "UPDATE Usuario SET nome = ?, senha = ? WHERE id = ?;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setLong(3, usuario.getId());
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            throw new Exception("Erro ao atualizar");
        }
    }

    @Override
    public Usuario getById(Long id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "SELECT id, login, nome FROM Usuario WHERE id = ?;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            Usuario usuario = new Usuario();

            while (rs.next()) {
                usuario.setId(rs.getLong(1));
                usuario.setLogin(rs.getString(2));
                usuario.setNome(rs.getString(3));
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return usuario;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public Usuario getByLogin(String login, String senha) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        
        try {
            String SQL = "SELECT id, login, senha, nome, tipo FROM Usuario WHERE login = ? AND senha = ? AND excluido = 0;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();

            Usuario usuario = new Usuario();

            while (rs.next()) {
                usuario.setId(rs.getLong(1));
                usuario.setLogin(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setNome(rs.getString(4));
                usuario.setTipo(TipoUsuario.valueOf(rs.getString(5)));
                usuario.setExcluido(false);
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return usuario;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        
        try {
            String SQL = "UPDATE Usuario SET excluido = ? WHERE id = ?;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setLong(1, 1);
            ps.setLong(2, id);
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps);
            throw new Exception("Erro ao deletar");
        }
    }

    @Override
    public boolean loginExists(String login) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            String SQL = "SELECT id FROM Usuario WHERE login = ? AND excluido = 0;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            ps.setString(1, login);
            rs = ps.executeQuery();
            
            boolean loginExiste = rs.next();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return loginExiste;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public List<Usuario> filter(String nome) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        
        try {
            StringBuilder sql = new StringBuilder();
            
            sql.append( " SELECT " );
            sql.append( " 	id, " );
            sql.append( " 	login, " );
            sql.append( " 	senha, " );
            sql.append( " 	nome, " );
            sql.append( " 	tipo " );
            sql.append( " FROM " );
            sql.append( " 	Usuario " );
            sql.append( " WHERE 1=1" );
            if (nome != null && !nome.isBlank()) {
                sql.append( " 	AND nome LIKE '%' || ? || '%' " );
            } 
            sql.append( " 	AND excluido = 0; " );

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql.toString());
            
            if (nome != null && !nome.isBlank()) {
                ps.setString(1, nome);
            }
            
            rs = ps.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();

            while (rs.next()) {
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getLong(1));
                usuario.setLogin(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setNome(rs.getString(4));
                usuario.setTipo(TipoUsuario.valueOf(rs.getString(5)));
                usuario.setExcluido(false);
                
                usuarios.add(usuario);
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return usuarios;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public boolean existeAdministradorAtivo() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        
        try {
            String sql = "SELECT COUNT(id) FROM Usuario WHERE excluido = 0 AND tipo = :tipoAdministrador;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, TipoUsuario.ADMINISTRADOR.name());
            
            rs = ps.executeQuery();
            
            boolean existemOutrosAdministradores = false;
            
            while (rs.next()) {
                existemOutrosAdministradores = rs.getLong(1) > 0;
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return existemOutrosAdministradores;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public boolean existemOutrosAdministradoresAtivos(Long idUsuario) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        
        try {
            String sql = "SELECT COUNT(id) FROM Usuario WHERE id <> :idUsuarioAExcluir AND excluido = 0 AND tipo = :tipoAdministrador;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql.toString());
            ps.setString(1, TipoUsuario.ADMINISTRADOR.name());
            
            rs = ps.executeQuery();
            
            boolean existemOutrosAdministradores = false;
            
            while (rs.next()) {
                existemOutrosAdministradores = rs.getLong(1) > 0;
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return existemOutrosAdministradores;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public boolean existemOutrosUsuariosAtivos(Long idUsuario) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        
        try {
            String sql = "SELECT COUNT(id) FROM Usuario WHERE id <> :idUsuarioAExcluir AND excluido = 0;";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(sql.toString());
            
            rs = ps.executeQuery();
            
            boolean existemOutrosUsuariosAtivos = false;
            
            while (rs.next()) {
                existemOutrosUsuariosAtivos = rs.getLong(1) > 0;
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return existemOutrosUsuariosAtivos;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }
    
    @Override
    public Usuario getUltimoInserido() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        
        try {
            String SQL = "SELECT id, login, senha, nome, tipo FROM Usuario WHERE excluido = 0 AND id = (SELECT MAX(id) FROM Usuario);";

            conn = this.manager.conectar();
            this.manager.abreTransacao();

            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();

            Usuario usuario = new Usuario();

            while (rs.next()) {
                usuario.setId(rs.getLong(1));
                usuario.setLogin(rs.getString(2));
                usuario.setSenha(rs.getString(3));
                usuario.setNome(rs.getString(4));
                usuario.setTipo(TipoUsuario.valueOf(rs.getString(5)));
                usuario.setExcluido(false);
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);

            return usuario;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close(conn, ps, rs);
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao buscar");
        }
    }
    
}
