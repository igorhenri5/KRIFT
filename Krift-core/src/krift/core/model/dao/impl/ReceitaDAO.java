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
import krift.common.model.domain.Procedimento;
import krift.common.model.domain.Receita;
import krift.core.model.dao.IIngredienteDAO;
import krift.core.model.dao.IProcedimentoDAO;
import krift.core.model.dao.IReceitaDAO;
import krift.core.model.dao.IUsuarioDAO;
import krift.core.util.db.JDBCConnectionManager;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public class ReceitaDAO implements IReceitaDAO{

    @Override
    public long inserir(Receita receita) throws PersistenciaException {
        Long id_image = null;
        Long id_receita = null;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
            IIngredienteDAO ingredienteDAO = new IngredienteDAO();
            String sql ="BEGIN;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
             sql ="INSERT INTO \"IMAGEM_RECEITA\"(\"ARQ_IMAGEM\")" +
                  "    VALUES (?)  returning \"SEQ_IMAGEM\";";

            statement = connection.prepareStatement(sql);

            statement.setBytes(1, receita.getImagem());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id_image = resultSet.getLong("SEQ_IMAGEM");
                receita.setSeq_imagem(id_image);
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel enviar a imagem");
            }
            
            sql = "INSERT INTO \"RECEITA\"(" +
                "            \"NOM_LOGIN\", \"NOM_RECEITA\", \"DES_RECEITA\", " +
                "            \"DAT_PUBLICACAO\", \"IDT_TENDENCIA\", \"QTD_TEMPO\", \"QTD_RENDIMENTO\")" +
                "    VALUES (?, ?, ?, " +
                "            ?, ?, ?, ?) returning \"NRO_SEQ_RECEITA\";";
            statement = connection.prepareStatement(sql);
            statement.setString(1, receita.getAutor().getNom_login());
            statement.setString(2, receita.getNom_receita());
            statement.setString(3, receita.getDes_receita());
            statement.setDate(4, receita.getDat_publicacao());
            statement.setString(5, receita.getIdt_tendencia());
            statement.setInt(6, receita.getQtd_tempo());
            statement.setInt(7, receita.getQtd_rendimento());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id_receita = resultSet.getLong("NRO_SEQ_RECEITA");
                receita.setNro_seq_receita(id_receita);
                statement.executeQuery();
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel cadastrar a receita");
            }
            
            for(Ingrediente ingrediente : receita.getIngredientes()){
                ingredienteDAO.inserir(ingrediente);
            }
            
            for(Procedimento procedimento : receita.getProcedimentos()){
                procedimentoDAO.inserir(procedimento);
            }
            
            sql ="COMMIT;";
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return id_receita;
    }

    @Override
    public void atualizar(Receita receita) throws PersistenciaException {
        Long id_image = null;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            IIngredienteDAO ingredienteDAO = new IngredienteDAO();
            IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
            String sql ="BEGIN;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeQuery();
             sql ="INSERT INTO \"IMAGEM_RECEITA\"(\"ARQ_IMAGEM\")" +
                  "    VALUES (?)  returning \"SEQ_IMAGEM\";";

            statement = connection.prepareStatement(sql);

            statement.setBytes(1, receita.getImagem());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id_image = resultSet.getLong("SEQ_IMAGEM");
                receita.setSeq_imagem(id_image);
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel enviar a imagem");
            }
            
            sql = "UPDATE \"RECEITA\" SET" +
                "            \"NOM_LOGIN\" = ?, \"NOM_RECEITA\" = ?, \"DES_RECEITA\" = ?, " +
                "            \"DAT_PUBLICACAO\" = ?, \"IDT_TENDENCIA\" = ?, \"QTD_TEMPO\" = ?, \"QTD_RENDIMENTO\" = ?" +
                "WHERE \"NRO_SEQ_RECEITA\" = ? returning \"NRO_SEQ_RECEITA\";";
            statement = connection.prepareStatement(sql);
            statement.setString(1, receita.getAutor().getNom_login());
            statement.setString(2, receita.getNom_receita());
            statement.setString(3, receita.getDes_receita());
            statement.setDate(4, receita.getDat_publicacao());
            statement.setString(5, receita.getIdt_tendencia());
            statement.setInt(6, receita.getQtd_tempo());
            statement.setInt(7, receita.getQtd_rendimento());
            statement.setLong(8, receita.getNro_seq_receita());
            resultSet = statement.executeQuery();

            if (!resultSet.next()){
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel alterar a receita");
            }   
            
            ingredienteDAO.excluir(receita.getIngredientes().get(0));
            for(Ingrediente ingrediente : receita.getIngredientes()){
                ingredienteDAO.inserir(ingrediente);
            }
            
            procedimentoDAO.excluir(receita.getProcedimentos().get(0));
            for(Procedimento procedimento : receita.getProcedimentos()){
                procedimentoDAO.inserir(procedimento);
            }
            
            sql ="COMMIT;";
            statement = connection.prepareStatement(sql);
            statement.executeQuery();
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public void excluir(long id) throws PersistenciaException {
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="DELETE FROM \"IMAGEM\" WHERE \"SEQ_IMAGEM\" = ("
                     + "SELECT \"SEQ_IMAGEM\" FROM \"RECEITA\" WHERE"
                     + "\"NRO_SEQ_RECEITA\" = ?;)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.executeQuery();
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
    }

    @Override
    public Receita consultarPorId(long id) throws PersistenciaException {
        Receita receita = null;
        try{
            IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
            IIngredienteDAO ingredienteDAO = new IngredienteDAO();
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT \"NRO_SEQ_RECEITA\", \"NOM_LOGIN\", \"SEQ_IMAGEM\", \"NOM_RECEITA\", " +
                        "       \"DES_RECEITA\", \"DAT_PUBLICACAO\", \"IDT_TENDENCIA\", \"QTD_TEMPO\", " +
                        "       \"QTD_RENDIMENTO\"" +
                        "  FROM public.\"RECEITA\"" +
                        "  NATURAL JOIN \"IMAGEM\"" +
                        "  WHERE \"NRO_SEQ_RECEITA\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
             statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                receita.setAutor(usuarioDAO.consultarPorNome(resultSet.getString("NOM_LOGIN")));
                receita.setSeq_imagem(resultSet.getLong("SEQ_IMAGEM"));
                receita.setNom_receita(resultSet.getString("NOM_RECEITA"));
                receita.setDes_receita(resultSet.getString("DES_RECEITA"));
                receita.setDat_publicacao(resultSet.getDate("DAT_PUBLICACAO"));
                receita.setIdt_tendencia(resultSet.getString("IDT_TENDENCIA"));
                receita.setQtd_tempo(resultSet.getInt("QTD_TEMPO"));
                receita.setQtd_rendimento(resultSet.getInt("QTD_RENDIMENTO"));
                receita.setImagem(resultSet.getBytes("ARQ_IMAGEM"));
                receita.setNro_seq_receita(resultSet.getLong("NRO_SEQ_RECEITA"));
                receita.setIngredientes(ingredienteDAO.listarPorReceita(id));
                receita.setProcedimentos(procedimentoDAO.listarPorReceita(id));
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return receita;
    }

    @Override
    public ArrayList<Receita> listarTodos() throws PersistenciaException {
        IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        IIngredienteDAO ingredienteDAO = new IngredienteDAO();
        ArrayList<Receita> receitas = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT \"NRO_SEQ_RECEITA\", \"NOM_LOGIN\", \"SEQ_IMAGEM\", \"NOM_RECEITA\", " +
                        "       \"DES_RECEITA\", \"DAT_PUBLICACAO\", \"IDT_TENDENCIA\", \"QTD_TEMPO\", " +
                        "       \"QTD_RENDIMENTO\"" +
                        "  FROM public.\"RECEITA\"" +
                        "  NATURAL JOIN \"IMAGEM\";";
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Receita receita = new Receita();
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                receita.setAutor(usuarioDAO.consultarPorNome(resultSet.getString("NOM_LOGIN")));
                receita.setSeq_imagem(resultSet.getLong("SEQ_IMAGEM"));
                receita.setNro_seq_receita(resultSet.getLong("NRO_SEQ_RECEITA"));
                receita.setNom_receita(resultSet.getString("NOM_RECEITA"));
                receita.setDes_receita(resultSet.getString("DES_RECEITA"));
                receita.setDat_publicacao(resultSet.getDate("DAT_PUBLICACAO"));
                receita.setIdt_tendencia(resultSet.getString("IDT_TENDENCIA"));
                receita.setQtd_tempo(resultSet.getInt("QTD_TEMPO"));
                receita.setQtd_rendimento(resultSet.getInt("QTD_RENDIMENTO"));
                receita.setImagem(resultSet.getBytes("ARQ_IMAGEM"));
                receita.setIngredientes(ingredienteDAO.listarPorReceita(receita.getNro_seq_receita()));
                receita.setProcedimentos(procedimentoDAO.listarPorReceita(receita.getNro_seq_receita()));
                receitas.add(receita);
            }
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return receitas;
    }
    
}
