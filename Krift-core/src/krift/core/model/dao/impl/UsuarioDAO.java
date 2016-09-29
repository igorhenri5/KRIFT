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
        System.out.println("CADASTRAR");
        
        Long id = null;
        boolean sucesso = false;
        
        System.out.println("NOME  "+usuario.getNom_login());
        System.out.println("EMAIL "+usuario.getEmail());
        System.out.println("SENHA "+usuario.getSenha());
        
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO usuario(" +
                        "            nom_login,nom_perfil_usuario," +
                        "            email, senha, seq_imagem)" +
                        "    VALUES (?, ?, ?, ?, ?) returning nom_login;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, usuario.getNom_login());
            statement.setString(2, usuario.getNom_login());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getSenha());
            statement.setLong(5, 0);

            //adicionar vinculo com a imagem padrão que será inserida no BD
            
            ResultSet resultSet = statement.executeQuery();

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
            statement.executeQuery();
             sql ="UPDATE imagem SET arq_imagem = ? returning seq_imagem;";

            statement = connection.prepareStatement(sql);

            statement.setBytes(1, usuario.getImagem());
            
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
                        "            email = ?, senha = ?, des_usuario = ?, idt_tendencia = ?," +
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
                statement.executeQuery();
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
                        "       pos_ranking, arq_imagem" +
                        "  FROM usuario" +
                        "  NATURAL JOIN imagem" +
                        "WHERE nom_login = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
             statement.setString(1, name);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setNom_login(resultSet.getString("seq_login"));
                usuario.setSeq_imagem(resultSet.getLong("seq_imagem"));
                usuario.setNom_perfil_usuario(resultSet.getString("nom_perfil_usuario"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setDes_usuario(resultSet.getString("des_usuario"));
                usuario.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                usuario.setNro_pontos(resultSet.getLong("nro_pontos"));
                usuario.setPos_ranking(resultSet.getInt("pos_ranking"));
                usuario.setImagem(resultSet.getBytes("arq_imagem"));
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
            String sql ="SELECT nom_login, seq_imagem, nom_perfil_usuario, dat_cadastro, " +
                        "       email, senha, des_usuario, idt_tendencia, nro_pontos, " +
                        "       pos_ranking, arq_imagem" +
                        "  FROM usuario" +
                        "  NATURAL JOIN imagem ORDER BY 10;";                        
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setNom_login(resultSet.getString("seq_login"));
                usuario.setSeq_imagem(resultSet.getLong("seq_imagem"));
                usuario.setNom_perfil_usuario(resultSet.getString("nom_perfil_usuario"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setDes_usuario(resultSet.getString("des_usuario"));
                usuario.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                usuario.setNro_pontos(resultSet.getLong("nro_pontos"));
                usuario.setPos_ranking(resultSet.getInt("pos_ranking"));
                usuario.setImagem(resultSet.getBytes("arq_imagem"));
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
        
        System.out.println("LOGAR");
        
        System.out.println("NOME  "+name);
        System.out.println("SENHA "+senha);
        
        boolean usuario = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
           
            String sql ="SELECT nom_login"  +
                        "  FROM usuario"  +
                        "WHERE nom_login = ? AND senha = ?;";
           
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
