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
            String sql = "INSERT INTO \"USUARIO\"(" +
                        "            \"NOM_LOGIN\"," +
                        "            \"EMAIL\", \"SENHA\")" +
                        "    VALUES (?, ?, ?) returning \"NOM_LOGIN\";";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNom_login());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getSenha());

            ResultSet resultSet = statement.executeQuery();

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
    public boolean atualizar(Usuario usuario) throws PersistenciaException {
        Long id = null;
        boolean sucesso = false;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="BEGIN;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
             sql ="UPDATE \"IMAGEM_USUARIO\" SET \"ARQ_IMAGEM\" = ? returning \"SEQ_IMAGEM\";";

            statement = connection.prepareStatement(sql);

            statement.setBytes(1, usuario.getImagem());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getLong("SEQ_IMAGEM");
                usuario.setSeq_imagem(id);
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel enviar a imagem");
            }
            
            sql = "UPDATE \"USUARIO\" SET " +
                        "            \"NOM_LOGIN\" = ?, \"SEQ_IMAGEM\" = ?, \"NOM_PERFIL_USUARIO\" = ?, \"DAT_CADASTRO\" = ?," +
                        "            \"EMAIL\" = ?, \"SENHA\" = ?, \"DES_USUARIO\" = ?, \"IDT_TENDENCIA\" = ?," +
                        "            \"NRO_PONTOS\" = ?, \"POS_RANKING\" = ? "
                    + "WHERE \"NOM_LOGIN\" LIKE ? returning \"NOM_LOGIN\";";
            statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getNom_login());
            statement.setLong(2, usuario.getSeq_imagem());
            statement.setString(3, usuario.getNom_perfil_usuario());
            statement.setDate(4, usuario.getDat_cadastro());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.getSenha());
            statement.setString(7, usuario.getDes_usuario());
            statement.setString(8, usuario.getIdt_tendencia());
            statement.setLong(9, usuario.getNro_pontos());
            statement.setLong(10, usuario.getPos_ranking());
            statement.setString(11, usuario.getNom_login());
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
            String sql ="SELECT \"NOM_LOGIN\", \"SEQ_IMAGEM\", \"NOM_PERFIL_USUARIO\", \"DAT_CADASTRO\", " +
                        "       \"EMAIL\", \"SENHA\", \"DES_USUARIO\", \"IDT_TENDENCIA\", \"NRO_PONTOS\", " +
                        "       \"POS_RANKING\", \"ARQ_IMAGEM\"" +
                        "  FROM \"USUARIO\"\n" +
                        "  NATURAL JOIN \"IMAGEM_USUARIO\"" +
                        "WHERE \"NOM_LOGIN\" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
             statement.setString(1, name);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                usuario.setNom_login(resultSet.getString("SEQ_LOGIN"));
                usuario.setSeq_imagem(resultSet.getLong("SEQ_IMAGEM"));
                usuario.setNom_perfil_usuario(resultSet.getString("NOM_PERFIL_USUARIO"));
                usuario.setDat_cadastro(resultSet.getDate("DAT_CADASTRO"));
                usuario.setEmail(resultSet.getString("EMAIL"));
                usuario.setSenha(resultSet.getString("SENHA"));
                usuario.setDes_usuario(resultSet.getString("DES_USUARIO"));
                usuario.setIdt_tendencia(resultSet.getString("IDT_TENDENCIA"));
                usuario.setNro_pontos(resultSet.getLong("NRO_PONTOS"));
                usuario.setPos_ranking(resultSet.getInt("POS_RANKING"));
                usuario.setImagem(resultSet.getBytes("ARQ_IMAGEM"));
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
            String sql ="SELECT \"NOM_LOGIN\", \"SEQ_IMAGEM\", \"NOM_PERFIL_USUARIO\", \"DAT_CADASTRO\", " +
                        "       \"EMAIL\", \"SENHA\", \"DES_USUARIO\", \"IDT_TENDENCIA\", \"NRO_PONTOS\", " +
                        "       \"POS_RANKING\", \"ARQ_IMAGEM\"" +
                        "  FROM \"USUARIO\"\n" +
                        "  NATURAL JOIN \"IMAGEM_USUARIO\" ORDER BY 10;";                        
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setNom_login(resultSet.getString("SEQ_LOGIN"));
                usuario.setSeq_imagem(resultSet.getLong("SEQ_IMAGEM"));
                usuario.setNom_perfil_usuario(resultSet.getString("NOM_PERFIL_USUARIO"));
                usuario.setDat_cadastro(resultSet.getDate("DAT_CADASTRO"));
                usuario.setEmail(resultSet.getString("EMAIL"));
                usuario.setSenha(resultSet.getString("SENHA"));
                usuario.setDes_usuario(resultSet.getString("DES_USUARIO"));
                usuario.setIdt_tendencia(resultSet.getString("IDT_TENDENCIA"));
                usuario.setNro_pontos(resultSet.getLong("NRO_PONTOS"));
                usuario.setPos_ranking(resultSet.getInt("POS_RANKING"));
                usuario.setImagem(resultSet.getBytes("ARQ_IMAGEM"));
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
            String sql ="SELECT \"NOM_LOGIN\", \"SEQ_IMAGEM\", \"NOM_PERFIL_USUARIO\", \"DAT_CADASTRO\", " +
                        "       \"EMAIL\", \"SENHA\", \"DES_USUARIO\", \"IDT_TENDENCIA\", \"NRO_PONTOS\", " +
                        "       \"POS_RANKING\", \"ARQ_IMAGEM\"" +
                        "  FROM \"USUARIO\"\n" +
                        "  NATURAL JOIN \"IMAGEM_USUARIO\"" +
                        "WHERE \"NOM_LOGIN\" = ? AND \"SENHA\" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
             statement.setString(1, name);
             statement.setString(1, senha);
            
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
