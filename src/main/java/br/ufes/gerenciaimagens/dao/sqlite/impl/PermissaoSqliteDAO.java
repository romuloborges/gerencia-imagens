package br.ufes.gerenciaimagens.dao.sqlite.impl;

import br.ufes.gerenciaimagens.dao.interfaces.IPermissaoDAO;
import br.ufes.gerenciaimagens.dao.manager.SqliteManager;
import br.ufes.gerenciaimagens.model.enums.TipoPermissao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author rborges
 */
public class PermissaoSqliteDAO implements IPermissaoDAO {
    
    private SqliteManager manager;
    
    public PermissaoSqliteDAO() {
        this.manager = new SqliteManager();
    }

    @Override
    public boolean possuiPermissao(Long idUsuario, Long idImagem, TipoPermissao tipoPermissao) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            
            sql.append("SELECT COUNT(*) FROM Permissao WHERE id_usuario = ? AND ");
            sql.append("tipo = ?");
            if (idImagem != null) {
                sql.append(" AND id_imagem = ? ");
            }
            sql.append(";");
            
            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, idUsuario);
            ps.setString(2, tipoPermissao.name());
            if (idImagem != null) {
                ps.setLong(3, idImagem);
            }
            
            ResultSet rs = ps.executeQuery();
            
            boolean possuiPermissao = false;
            
            while(rs.next()) {
                possuiPermissao = rs.getLong(1) > 0;
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return possuiPermissao;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao carregar permissão");
        }
    }
    
    @Override
    public boolean possuiPermissaoEmTodasImagens(Long idUsuario, TipoPermissao tipoPermissao) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            
            sql.append( " SELECT " );
            sql.append( " 	COUNT(*) " );
            sql.append( " FROM " );
            sql.append( " 	( " );
            sql.append( " 	SELECT " );
            sql.append( " 		i.id " );
            sql.append( " 	FROM " );
            sql.append( " 		Imagem i " );
            sql.append( " EXCEPT " );
            sql.append( " 	SELECT " );
            sql.append( " 		p.id_imagem " );
            sql.append( " 	FROM " );
            sql.append( " 		Permissao p " );
            sql.append( " 	WHERE " );
            sql.append( " 		id_usuario = ? " );
            sql.append( " 		AND tipo = ?); " );
            
            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, idUsuario);
            ps.setString(2, tipoPermissao.name());
            
            ResultSet rs = ps.executeQuery();
            
            boolean possuiPermissao = false;
            
            while(rs.next()) {
                possuiPermissao = rs.getLong(1) == 0;
            }

            this.manager.fechaTransacao();
            this.manager.close(conn, ps, rs);
            
            return possuiPermissao;
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao carregar permissão");
        }
    }

    @Override
    public void concederPermissaoAoUsuario(List<TipoPermissao> permissoes, Long idUsuario, Long idImagem) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            
            StringBuilder permissoesString = new StringBuilder();
            
            for(TipoPermissao tipoPermissao : permissoes) {
                permissoesString.append("('").append(tipoPermissao.name()).append("'),");
            }
            
            sql.append( " INSERT INTO " );
            sql.append( " 	Permissao(id_imagem, id_usuario, tipo) WITH TipoPermissao(tipo_permissao) AS ( " );
            sql.append( " VALUES ").append(permissoesString.toString().substring(0, permissoesString.length() - 1)).append(" )");
            sql.append( " SELECT " );
            sql.append( " 	i.id as id_imagem, " );
            sql.append( " 	id_usuario, " );
            sql.append( " 	tipo_permissao " );
            sql.append( " FROM " );
            sql.append( " 	Imagem i " );
            sql.append( " CROSS JOIN ( " );
            sql.append( " 	select " );
            sql.append( " 		(?) as id_usuario, tipo_permissao " );
            sql.append( " 	FROM " );
            sql.append( " 		(TipoPermissao)) " );
            sql.append( " WHERE 1=1 AND" );
            if (idImagem != null) {
                sql.append( " 	i.id = ? AND " );
            }
            sql.append( " 	(id_imagem, " );
            sql.append( " 	id_usuario, " );
            sql.append( " 	tipo_permissao) NOT IN ( " );
            sql.append( " 	SELECT " );
            sql.append( " 		id_usuario, id_imagem, tipo " );
            sql.append( " 	FROM " );
            sql.append( " 		Permissao " );
            sql.append( " 	WHERE " );
            sql.append( " 		id_usuario = ? " );
            if (idImagem != null) {
                sql.append( " 		AND id_imagem = ? " );
            }
            sql.append(");");
            
            System.out.println(sql.toString());

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, idUsuario);
            int parametroAtual = 2;
            
            if (idImagem != null) {
                ps.setLong(parametroAtual, idImagem);
                parametroAtual++;
            }
            
            ps.setLong(parametroAtual, idUsuario);
            parametroAtual++;
            
            if (idImagem != null) {
                ps.setLong(parametroAtual, idImagem);
                parametroAtual++;
            }
            
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao conceder permissões");
        }
    }

    @Override
    public void removerPermissoes(Long idUsuario, Long idImagem) throws Exception {
        try {
            StringBuilder sql = new StringBuilder();
            
            sql.append( " DELETE " );
            sql.append( " FROM " );
            sql.append( " 	Permissao " );
            sql.append( " WHERE " );
            sql.append( " 	id_usuario = :id_usuario " );
            if (idImagem != null) {
                sql.append( " 	AND id_imagem = :id_imagem " );
            }
            sql.append(";");

            Connection conn = this.manager.conectar();
            this.manager.abreTransacao();

            PreparedStatement ps = conn.prepareStatement(sql.toString());
            ps.setLong(1, idUsuario);
            
            if (idImagem != null) {
                ps.setLong(2, idImagem);
            }
            
            ps.executeUpdate();

            this.manager.fechaTransacao();
            this.manager.close(conn, ps);
        } catch (Exception ex) {
            this.manager.desfazTransacao();
            this.manager.close();
            System.out.println(ex.getMessage());
            throw new Exception("Erro ao remover permissões antigas");
        }
    }
    
}