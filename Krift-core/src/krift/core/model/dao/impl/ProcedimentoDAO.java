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
import krift.common.model.domain.Procedimento;
import krift.core.model.dao.IProcedimentoDAO;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class ProcedimentoDAO implements IProcedimentoDAO{

    @Override
    public long inserir(Procedimento procedimento) throws PersistenciaException {
        long id;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO procedimento(" +
                "            nro_seq_receita, des_procedimento)" +
                "    VALUES (?, ?) returning nro_seq_procedimento;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, procedimento.getNro_seq_receita());
            statement.setString(2, procedimento.getDes_procedimento());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                procedimento.setNro_seq_procedimento(resultSet.getLong("nro_seq_procedimento"));
                id = procedimento.getNro_seq_procedimento();
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                statement.execute();
                throw new PersistenciaException("Não foi possivel cadastrar os procedimentos da receira");
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return id;
    }

    @Override
    public void excluir(Procedimento procedimento) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM procedimento WHERE " +
                "            nro_seq_receita = ? AND nro_seq_procedimento = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, procedimento.getNro_seq_receita());
            statement.setLong(2, procedimento.getNro_seq_procedimento());
            statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    public ArrayList<Procedimento> listarPorReceita(long nro_seq_receita) throws PersistenciaException {
        ArrayList<Procedimento> procedimentos = null;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "SELECT nro_seq_procedimento, des_procedimento FROM procedimento WHERE " +
                "            nro_seq_receita = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, nro_seq_receita);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Procedimento procedimento = new Procedimento();
                procedimento.setDes_procedimento(resultSet.getString("des_procedimento"));
                procedimento.setNro_seq_receita(nro_seq_receita);
                procedimento.setNro_seq_procedimento(resultSet.getLong("nro_seq_procedimento"));
                procedimentos.add(procedimento);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return procedimentos;
    }
    
}
