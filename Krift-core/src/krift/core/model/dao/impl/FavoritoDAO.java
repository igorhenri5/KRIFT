/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao.impl;

import krift.core.model.dao.IFavoritoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import krift.common.model.domain.Receita;
import krift.core.model.dao.IReceitaDAO;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class FavoritoDAO implements IFavoritoDAO {
    @Override
    public ArrayList<Receita> listarFavoritos(String nom_login) throws PersistenciaException{
        ArrayList<Receita> receitas = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            IReceitaDAO receitaDAO = new ReceitaDAO();
            String sql = "SELECT nro_seq_receita FROM favorito WHERE " +
                "            nom_login = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom_login);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                receitas.add(receitaDAO.consultarPorId(resultSet.getLong("nro_seq_receita")));
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return receitas;
    }

    @Override
    public boolean inserir(String nom_login, long nro_seq_receita) throws PersistenciaException{
        boolean sucesso = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO favorito(" +
                    "            nro_seq_receita, nom_login)" +
                    "    VALUES (?, ?) returning 1;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, nro_seq_receita);
            statement.setString(2, nom_login);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                statement.execute();
                throw new PersistenciaException("Não foi possivel adicionar aos favoritos");
            }
            sql ="COMMIT;";
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
            sucesso = true;
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sucesso;
    }
    
    @Override
    public boolean excluir(String nom_login, long nro_seq_receita) throws PersistenciaException{
        boolean sucesso = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM favorito  WHERE " +
                "   nro_seq_receita AND nom_login = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, nro_seq_receita);
            statement.setString(2, nom_login);
            statement.executeQuery();
            sucesso = true;
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sucesso;
    }
}
