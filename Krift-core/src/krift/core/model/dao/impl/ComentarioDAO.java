/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao.impl;

import krift.core.model.dao.IComentarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import krift.common.model.domain.Comentario;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class ComentarioDAO implements IComentarioDAO{

    @Override
    public void comentar(Comentario comentario) throws PersistenciaException {
        
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="INSERT INTO \"COMENTARIO\"(\"NRO_SEQ_RECEITA\",\"NOM_LOGIN\", \"DES_COMENTARIO\")" +
                  "    VALUES (?,?,?)  returning \"DAT_PUBLICACAO\";";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, comentario.getNro_seq_receita());
            statement.setString(2, comentario.getNom_login());
            statement.setString(3, comentario.getDes_comentario());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                comentario.setDat_publicacao(resultSet.getDate("DAT_PUBLICACAO"));
            }else{
                throw new PersistenciaException("Não foi possivel comentar a receita");
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public ArrayList<Comentario> comentariosDaReceita(Comentario comentario) throws PersistenciaException {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT \"NRO_SEQ_RECEITA\",\"NOM_LOGIN\",\"DES_COMENTARIO\", \"DAT_PUBLICACAO\" FROM \"COMENTARIO\"" +
                  "    WHERE \"NRO_SEQ_RECEITA\" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, comentario.getNro_seq_receita());
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Comentario coment = new Comentario();
                coment.setDat_publicacao(resultSet.getDate("DAT_PUBLICACAO"));
                coment.setDes_comentario(resultSet.getString("DES_COMENTARIO"));
                coment.setNom_login(resultSet.getString("NOM_LOGIN"));
                coment.setNro_seq_receita(resultSet.getLong("NRO_SEQ_RECEITA"));
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return comentarios;
    }
    
    @Override
    public void detetarComentario(Comentario comentario) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="DELETE FROM \"COMENTARIO\"" +
                  "    WHERE \"NRO_SEQ_RECEITA\" = ? AND \"NOM_LOGIN\" =? AND "
                    + " \"DAT_PUBLICACAO\" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, comentario.getNro_seq_receita());
            statement.setString(2, comentario.getNom_login());
            statement.setDate(3, comentario.getDat_publicacao());
            statement.executeQuery();
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
