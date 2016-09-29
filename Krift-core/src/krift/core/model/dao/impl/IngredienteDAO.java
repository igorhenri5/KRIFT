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
            String sql = "INSERT INTO ingrediente(" +
                    "            nro_seq_receita, des_quantidade, nom_ingrediente)" +
                    "    VALUES (?, ?, ?) returning nro_seq_ingrediente;";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1, ingrediente.getNro_seq_receita());
                statement.setString(2, ingrediente.getDes_quantidade());
                statement.setString(3, ingrediente.getNom_ingrediente());
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    ingrediente.setNro_seq_ingrediente(resultSet.getLong("nro_seq_ingrediente"));
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
            String sql = "DELETE FROM ingrediente WHERE " +
                "            nro_seq_receita = ? AND nro_seq_ingrediente = ?;";
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
            String sql = "SELECT nro_seq_receita, des_quantidade, nom_ingrediente FROM ingrediente WHERE " +
                "            nro_seq_receita = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, nro_seq_receita);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Ingrediente ing = new Ingrediente();
                ing.setDes_quantidade(resultSet.getString("des_quantidade"));
                ing.setNom_ingrediente(resultSet.getString("nom_ingrediente"));
                ing.setNro_seq_receita(nro_seq_receita); 
                ingredientes.add(ing);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return ingredientes;
    }
}
