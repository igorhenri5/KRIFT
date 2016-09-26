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
import krift.common.model.domain.Receita;
import krift.core.model.dao.IHistoricoDAO;
import krift.core.model.dao.IReceitaDAO;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class HistoricoDAO implements IHistoricoDAO {
    
    @Override
    public ArrayList<Receita> listarHistorico(String nom_login) throws PersistenciaException{
        ArrayList<Receita> receitas = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            IReceitaDAO receitaDAO = new ReceitaDAO();
            String sql = "SELECT \"NRO_SEQ_RECEITA\" FROM \"HISTORICO\" WHERE " +
                "            \"NOM_LOGIN\" = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom_login);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                receitas.add(receitaDAO.consultarPorId(resultSet.getLong("NRO_SEQ_RECEITA")));
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return receitas;
    }

    @Override
    public void inserir(String nom_login, long nro_seq_receita) throws PersistenciaException{
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO \"HISTORICO\"(" +
                    "            \"NRO_SEQ_RECEITA\", \"NOM_LOGIN\")" +
                    "    VALUES (?, ?) returning \"DAT_ACESSO\";";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, nro_seq_receita);
            statement.setString(2, nom_login);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()){
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                statement.execute();
                throw new PersistenciaException("Não foi possivel adicionar ao historico");
            }
            sql ="COMMIT;";
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
            
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }
}
