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
import krift.common.model.domain.Ingrediente;
import krift.core.model.dao.IIngredienteDAO;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class IngredienteDAO implements IIngredienteDAO{

    @Override
    public long inserir(Ingrediente ingrediente) throws PersistenciaException {
        long id;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO \"INGREDIENTE\"(" +
                    "            \"NRO_SEQ_RECEITA\", \"DES_QUANTIDADE\", \"NOM_INGREDIENTE\")" +
                    "    VALUES (?, ?, ?) returning \"NRO_SEQ_INGREDIENTE\";";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, ingrediente.getNro_seq_receita());
                statement.setString(2, ingrediente.getDes_quantidade());
                statement.setString(3, ingrediente.getNom_ingrediente());
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    ingrediente.setNro_seq_ingrediente(resultSet.getLong("NRO_SEQ_INGREDIENTE"));
                    id = ingrediente.getNro_seq_ingrediente();
                }else{
                    sql ="ROLLBACK;";
                    statement = connection.prepareStatement(sql);
                    statement.execute();
                    throw new PersistenciaException("Não foi possivel cadastrar os ingredientes da receita");
                }
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return id;
    }

    @Override
    public void excluir(Ingrediente ingrediente) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM \"INGREDIENTE\" WHERE " +
                "            \"NRO_SEQ_RECEITA\" = ? AND \"NRO_SEQ_INGREDIENTE\" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, ingrediente.getNro_seq_receita());
            statement.setLong(2, ingrediente.getNro_seq_ingrediente());
            statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
    
    @Override
    public ArrayList<Ingrediente> listarPorReceita(long nro_seq_receita) throws PersistenciaException {
        ArrayList<Ingrediente> ingredientes = null;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "SELECT \"NRO_SEQ_INGREDIENTE\" \"DES_QUANTIDADE\", \"NOM_INGREDIENTE\" FROM \"INGREDIENTE\" WHERE " +
                "            \"NRO_SEQ_RECEITA\" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, nro_seq_receita);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Ingrediente ing = new Ingrediente();
                ing.setDes_quantidade(resultSet.getString("DES_QUANTIDADE"));
                ing.setNom_ingrediente(resultSet.getString("NOM_INGREDIENTE"));
                ing.setNro_seq_receita(nro_seq_receita);
                ing.setNro_seq_receita(resultSet.getLong("NRO_SEQ_INGREDIENTE"));
                ingredientes.add(ing);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return ingredientes;
    }
}
