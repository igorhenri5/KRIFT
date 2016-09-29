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
    public boolean avaliar(Avaliacao avaliacao) throws PersistenciaException {
        boolean sucesso = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="INSERT INTO avaliacao(nro_seq_receita,nom_login, vlr_avaliacao)" +
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
            
            sucesso = true;
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sucesso;
    }

    @Override
    public boolean alterar(Avaliacao avaliacao) throws PersistenciaException {
        boolean sucesso = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="UPDATE avaliacao SET vlr_avaliacao = ?" +
                  "    WHERE nro_seq_receita = ? AND nom_login = ?  returning 1;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setInt(1, avaliacao.getVlr_avaliacao());
            statement.setLong(2, avaliacao.getNro_seq_receita());
            statement.setString(3, avaliacao.getNom_login());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (!resultSet.next()) {
                throw new PersistenciaException("Não foi possivel avaliar a receita");
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
    public Avaliacao consultaAvaliacao(Avaliacao avaliacao) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT vlr_avaliacao FROM avaliacao WHERE " +
                  "    WHERE nro_seq_receita = ? AND nom_login = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, avaliacao.getNro_seq_receita());
            statement.setString(2, avaliacao.getNom_login());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                avaliacao.setVlr_avaliacao(resultSet.getInt("vlr_avaliacao"));
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
            String sql ="SELECT AVG(vlr_avaliacao) as vlr FROM avaliacao WHERE " +
                  "    WHERE nro_seq_receita = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, avaliacao.getNro_seq_receita());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                avaliacao.setVlr_avaliacao(resultSet.getInt("vlr"));
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
    public boolean denunciar(Denuncia denuncia) throws PersistenciaException {
        boolean sucesso = false;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="INSERT INTO denuncia(nro_seq_receita,des_comentario)" +
                  "    VALUES (?,?)  returning nro_seq_denuncia;";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setLong(1, denuncia.getNro_seq_receita());
            statement.setString(2, denuncia.getDes_comentario());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                denuncia.setSeq_denuncia(resultSet.getLong("nro_seq_denuncia"));
            }else{
                throw new PersistenciaException("Não foi possivel registrar a denuncia");
            }
            
            connection.close();
            sucesso = true;
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return sucesso;
    }
    
    
}
