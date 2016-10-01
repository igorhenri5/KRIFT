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
    public boolean comentar(Comentario comentario) throws PersistenciaException {
        boolean sucesso = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="INSERT INTO comentario(nro_seq_receita,nom_login, des_comentario)" +
                  "    VALUES (?,?,?)  returning dat_publicacao;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, comentario.getNro_seq_receita());
            statement.setString(2, comentario.getNom_login());
            statement.setString(3, comentario.getDes_comentario());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                comentario.setDat_publicacao(resultSet.getDate("dat_publicacao"));
            }else{
                throw new PersistenciaException("Não foi possivel comentar a receita");
            }
            
            connection.close();
            sucesso = true;
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sucesso;
    }
    
    @Override
    public ArrayList<Comentario> comentariosDaReceita(long nro_seq_receita) throws PersistenciaException {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT nro_seq_receita,nom_login,des_comentario, dat_publicacao FROM comentario" +
                  "    WHERE nro_seq_receita = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, nro_seq_receita);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Comentario coment = new Comentario();
                coment.setDat_publicacao(resultSet.getDate("dat_publicacao"));
                coment.setDes_comentario(resultSet.getString("des_comentario"));
                coment.setNom_login(resultSet.getString("nom_login"));
                coment.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return comentarios;
    }
    
    @Override
    public boolean detetarComentario(Comentario comentario) throws PersistenciaException {
        boolean sucesso = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="DELETE FROM comentario" +
                  "    WHERE nro_seq_receita = ? AND nom_login =? AND "
                    + " dat_publicacao = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, comentario.getNro_seq_receita());
            statement.setString(2, comentario.getNom_login());
            statement.setDate(3, comentario.getDat_publicacao());
            statement.executeQuery();
            
            connection.close();
            sucesso = true;
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return true;
    }
}
