/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import krift.common.model.domain.Avaliacao;
import krift.common.model.domain.Denuncia;
import krift.core.model.dao.IAvaliacaoDAO;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class AvaliacaoDAO implements IAvaliacaoDAO{

    @Override
    public void avaliar(Avaliacao avaliacao) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="INSERT INTO \"AVALIACAO\"(\"NRO_SEQ_RECEITA\",\"NOM_LOGIN\", \"VLR_AVALIACAO\")" +
                  "    VALUES (?,?,?)  returning 1;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, avaliacao.getNro_seq_receita());
            statement.setString(2, avaliacao.getNom_login());
            statement.setInt(3, avaliacao.getVlr_avaliacao());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (!resultSet.next()) {
                throw new PersistenciaException("Não foi possivel avaliar a receita");
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public void alterar(Avaliacao avaliacao) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="UPDATE \"AVALIACAO\" SET \"VLR_AVALIACAO\" = ?" +
                  "    WHERE NRO_SEQ_RECEITA = ? AND NOM_LOGIN = ?  returning 1;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, avaliacao.getVlr_avaliacao());
            statement.setLong(2, avaliacao.getNro_seq_receita());
            statement.setString(3, avaliacao.getNom_login());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (!resultSet.next()) {
                throw new PersistenciaException("Não foi possivel avaliar a receita");
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public Avaliacao consultaAvaliacao(Avaliacao avaliacao) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT \"VLR_AVALIACAO\" FROM \"AVALIACAO\" WHERE " +
                  "    WHERE NRO_SEQ_RECEITA = ? AND NOM_LOGIN = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, avaliacao.getNro_seq_receita());
            statement.setString(2, avaliacao.getNom_login());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                avaliacao.setVlr_avaliacao(resultSet.getInt("VLR_AVALIACAO"));
            }else{
                avaliacao.setVlr_avaliacao(0);
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return avaliacao;
    }
    
    @Override
    public Avaliacao avaliacaoReceita(Avaliacao avaliacao) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT AVG(\"VLR_AVALIACAO\") as VLR FROM \"AVALIACAO\" WHERE " +
                  "    WHERE NRO_SEQ_RECEITA = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, avaliacao.getNro_seq_receita());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                avaliacao.setVlr_avaliacao(resultSet.getInt("VLR"));
            }else{
                throw new PersistenciaException("Não foi possivel calcular a avaliação da receita");
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return avaliacao;
    }
    
    @Override
    public long denunciar(Denuncia denuncia) throws PersistenciaException {
        Long id = null;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="INSERT INTO \"DENUNCIA\"(\"NRO_SEQ_RECEITA\",\"DES_COMENTARIO\")" +
                  "    VALUES (?,?)  returning \"SEQ_DENUNCIA\";";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, denuncia.getNro_seq_receita());
            statement.setString(2, denuncia.getDes_comentario());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id = resultSet.getLong("NRO_SEQ_DENUNCIA");
                denuncia.setSeq_denuncia(id);
            }else{
                throw new PersistenciaException("Não foi possivel registrar a denuncia");
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return id;
    }
    
    
}
