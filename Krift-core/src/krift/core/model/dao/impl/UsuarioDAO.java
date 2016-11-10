/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import krift.common.model.domain.Usuario;
import krift.core.model.dao.IUsuarioDAO;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class UsuarioDAO implements IUsuarioDAO{

    @Override
    public boolean inserir(Usuario usuario) throws PersistenciaException {
        Long id = null;
        boolean sucesso = false;
        
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO imagem(arq_imagem)" +
                        "    VALUES (decode('iVBORw0KGgoAAAANSUhEUgAAAgAAAAIACAYAAAD0eNT6AAAgAElEQVR4Ae3dXZLbOLIGUN2J2UCHI3oFfvH+1zBrmBevoCM6vIR7A77NsqzSD0kRIDLz6MVVEkUCJ0HyE0iV/+c///nP/148CBAgQIAAgVIC/yrVW50lQIAAAQIEfgoIAAYCAQIECBAoKCAAFCy6LhMgQIAAAQHAGCBAgAABAgUFBICCRddlAgQIECAgABgDBAgQIECgoIAAULDoukyAAAECBAQAY4AAAQIECBQUEAAKFl2XCRAgQICAAGAMECBAgACBggICQMGi6zIBAgQIEBAAjAECBAgQIFBQQAAoWHRdJkCAAAECAoAxQIAAAQIECgoIAAWLrssECBAgQEAAMAYIECBAgEBBAQGgYNF1mQABAgQICADGAAECBAgQKCggABQsui4TIECAAAEBwBggQIAAAQIFBQSAgkXXZQIECBAgIAAYAwQIECBAoKCAAFCw6LpMgAABAgQEAGOAAAECBAgUFBAAChZdlwkQIECAgABgDBAgQIAAgYICAkDBousyAQIECBAQAIwBAgQIECBQUEAAKFh0XSZAgAABAgKAMUCAAAECBAoKCAAFi67LBAgQIEBAADAGCBAgQIBAQQEBoGDRdZkAAQIECAgAxgABAgQIECgoIAAULLouEyBAgAABAcAYIECAAAECBQUEgIJF12UCBAgQICAAGAMECBAgQKCggABQsOi6TIAAAQIEBABjgAABAgQIFBQQAAoWXZcJECBAgIAAYAwQIECAAIGCAgJAwaLrMgECBAgQEACMAQIECBAgUFBAAChYdF0mQIAAAQICgDFAgAABAgQKCggABYuuywQIECBAQAAwBggQIECAQEEBAaBg0XWZAAECBAgIAMYAAQIECBAoKCAAFCy6LhMgQIAAAQHAGCBAgAABAgUFBICCRddlAgQIECAgABgDBAgQIECgoIAAULDoukyAAAECBAQAY4AAAQIECBQUEAAKFl2XCRAgQICAAGAMECBAgACBggICQMGi6zIBAgQIEBAAjAECBAgQIFBQQAAoWHRdJkCAAAECAoAxQIAAAQIECgoIAAWLrssECBAgQEAAMAYIECBAgEBBAQGgYNF1mQABAgQICADGAAECBAgQKCggABQsui4TIECAAAEBwBggQIAAAQIFBQSAgkXXZQIECBAg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','base64')) returning seq_imagem;";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getLong("seq_imagem");
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel cadastrar o Usuario");
            }
            
            sql = "INSERT INTO usuario(" +
                        "            nom_login,nom_perfil_usuario," +
                        "            email, senha, seq_imagem, pos_ranking)" +
                        "    VALUES (?, ?, ?, ?, ?, ?) returning nom_login;";
            statement = connection.prepareStatement(sql);
            
            statement.setString(1, usuario.getNom_login());
            statement.setString(2, usuario.getNom_login());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getSenha());
            statement.setLong(5, id);
            statement.setLong(6, 0);

            //adicionar vinculo com a imagem padrão que será inserida no BD
            
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sucesso = true;
                sql ="COMMIT;";
                statement = connection.prepareStatement(sql);
                statement.execute();
                connection.commit();
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel cadastrar o Usuario");
            }
            
            
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return sucesso;
    }

    @Override
    public boolean atualizar(Usuario usuario) throws PersistenciaException {
        Long id = null;
        boolean sucesso = false;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="BEGIN;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            System.err.println(usuario.getImagem());
            
            sql ="UPDATE imagem SET arq_imagem = decode(?,'base64') WHERE seq_imagem = ? returning seq_imagem;";

            statement = connection.prepareStatement(sql);
            
            statement.setString(1, usuario.getImagem());
            statement.setLong(2, usuario.getSeq_imagem());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getLong("seq_imagem");
                usuario.setSeq_imagem(id);
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel enviar a imagem");
            }
            
            sql = "UPDATE usuario SET " +
                        "            nom_login = ?, seq_imagem = ?, nom_perfil_usuario = ?, " +
                        "            email = ?, senha = ?, des_usuario = ?, idt_tendencia = ? " +
                        "WHERE nom_login = ? returning nom_login;";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNom_login());
            statement.setLong(2, usuario.getSeq_imagem());
            statement.setString(3, usuario.getNom_perfil_usuario());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getSenha());
            statement.setString(6, usuario.getDes_usuario());
            statement.setString(7, usuario.getIdt_tendencia());
            statement.setString(8, usuario.getNom_login());
            
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sucesso = true;
                sql ="COMMIT;";
                statement = connection.prepareStatement(sql);
                //statement.executeQuery();
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel cadastrar o Usuario");
            }

            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sucesso;
    }

    @Override
    public Usuario consultarPorNome(String name) throws PersistenciaException {
        Usuario usuario = null;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT nom_login, seq_imagem, nom_perfil_usuario, " +
                        "       email, senha, des_usuario, idt_tendencia, nro_pontos, " +
                        "       pos_ranking, encode(arq_imagem, 'base64') as arq_imagem" +
                        "  FROM usuario" +
                        "  NATURAL JOIN imagem " +
                        "WHERE nom_login = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
             statement.setString(1, name);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setNom_login(resultSet.getString("nom_login"));
                usuario.setSeq_imagem(resultSet.getLong("seq_imagem"));
                usuario.setNom_perfil_usuario(resultSet.getString("nom_perfil_usuario"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setDes_usuario(resultSet.getString("des_usuario"));
                usuario.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                usuario.setNro_pontos(resultSet.getLong("nro_pontos"));
                usuario.setPos_ranking(resultSet.getInt("pos_ranking"));
                usuario.setImagem(resultSet.getString("arq_imagem"));
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return usuario;
    }

    @Override
    public ArrayList<Usuario> listarTodos() throws PersistenciaException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT nom_login, seq_imagem, nom_perfil_usuario, " +
                        "       email, senha, des_usuario, idt_tendencia, nro_pontos, " +
                        "       pos_ranking, encode(arq_imagem,'base64') as arq_imagem" +
                        "  FROM usuario" +
                        "  NATURAL JOIN imagem ORDER BY 9;";                        
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setNom_login(resultSet.getString("nom_login"));
                usuario.setSeq_imagem(resultSet.getLong("seq_imagem"));
                usuario.setNom_perfil_usuario(resultSet.getString("nom_perfil_usuario"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setDes_usuario(resultSet.getString("des_usuario"));
                usuario.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                usuario.setNro_pontos(resultSet.getLong("nro_pontos"));
                usuario.setPos_ranking(resultSet.getInt("pos_ranking"));
                usuario.setImagem(resultSet.getString("arq_imagem"));
                usuarios.add(usuario);
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return usuarios;
    }

    @Override
    public boolean login(String name, String senha) throws PersistenciaException {
        
        boolean usuario = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
           
            String sql ="SELECT nom_login"  +
                        "  FROM usuario "  +
                        "WHERE nom_login LIKE ? AND senha LIKE ?;";
           
            PreparedStatement statement = connection.prepareStatement(sql);
            
             statement.setString(1, name);
             statement.setString(2, senha);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                usuario = true;
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return usuario;
    }
    
}
