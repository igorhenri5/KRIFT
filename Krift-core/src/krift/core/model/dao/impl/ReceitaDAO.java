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
    public boolean inserir(Receita receita) throws PersistenciaException {
        Long id_image = null;
        boolean sucesso = false;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
            IIngredienteDAO ingredienteDAO = new IngredienteDAO();
            
            String sql ="INSERT INTO imagem (arq_imagem)" +
                  "    VALUES (decode(?,'base64'))  returning seq_imagem;";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement = connection.prepareStatement(sql);

            statement.setString(1, receita.getImagem());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                id_image = resultSet.getLong("seq_imagem");
                receita.setSeq_imagem(id_image);                
            }else{
                throw new PersistenciaException("Não foi possivel enviar a imagem");
            }
            
            sql = "INSERT INTO receita(" +
                "           nom_login, nom_receita, des_receita, " +
                "            idt_tendencia, qtd_tempo, qtd_rendimento, seq_imagem)" +
                "    VALUES (?, ?, ?, " +
                "             ?, ?, ?,?) returning nro_seq_receita;";
            
            statement = connection.prepareStatement(sql);
            statement.setString(1, receita.getAutor().getNom_login());
            statement.setString(2, receita.getNom_receita());
            statement.setString(3, receita.getDes_receita());
            statement.setString(4, receita.getIdt_tendencia());
            statement.setInt(5, receita.getQtd_tempo());
            statement.setString(6, receita.getQtd_rendimento());
            statement.setLong(7,receita.getSeq_imagem());

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                sucesso = true;
                receita.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
            }else{
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel cadastrar a receita");
            }
            
            for(Ingrediente ingrediente : receita.getIngredientes()){
                ingrediente.setNro_seq_receita(receita.getNro_seq_receita());
                ingredienteDAO.inserir(ingrediente);                
            }
            
            for(Procedimento procedimento : receita.getProcedimentos()){
                procedimento.setNro_seq_receita(receita.getNro_seq_receita());
                procedimentoDAO.inserir(procedimento);
            }
            
            sql ="COMMIT;";
            statement = connection.prepareStatement(sql);
           // statement.executeQuery();
            
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }

        return sucesso;
    }

    @Override
    public boolean atualizar(Receita receita) throws PersistenciaException {
        Long id_image = null;
        boolean sucesso = false;

        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            IIngredienteDAO ingredienteDAO = new IngredienteDAO();
            IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
            String sql ="UPDATE imagem SET arq_imagem = (decode(?,'base64')) "
                    + "WHERE seq_imagem = ? returning seq_imagem;";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, receita.getImagem());
            statement.setLong(2, receita.getSeq_imagem());
            
            ResultSet resultSet = statement.executeQuery();
            
            if (!resultSet.next()) {
                throw new PersistenciaException("Não foi possivel enviar a imagem");
            }
            
            sql = "UPDATE receita SET" +
                "            nom_receita = ?, des_receita = ?, " +
                "            dat_publicacao = ?, idt_tendencia = ?, qtd_tempo = ?, qtd_rendimento = ?" +
                "WHERE nro_seq_receita = ? returning nro_seq_receita;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, receita.getNom_receita());
            statement.setString(2, receita.getDes_receita());
            statement.setDate(3, receita.getDat_publicacao());
            statement.setString(4, receita.getIdt_tendencia());
            statement.setInt(5, receita.getQtd_tempo());
            statement.setString(6, receita.getQtd_rendimento());
            statement.setLong(7, receita.getNro_seq_receita());
            resultSet = statement.executeQuery();

            if (!resultSet.next()){
                sql ="ROLLBACK;";
                statement = connection.prepareStatement(sql);
                throw new PersistenciaException("Não foi possivel alterar a receita");
            }   
            
            for(Ingrediente ingrediente : receita.getIngredientes()){
                ingrediente.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
            }
            ingredienteDAO.excluir(receita.getIngredientes().get(0));
            for(Ingrediente ingrediente : receita.getIngredientes()){
                ingredienteDAO.inserir(ingrediente);
            }
            for(Procedimento procedimento : receita.getProcedimentos()){
                procedimento.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
            }
            procedimentoDAO.excluir(receita.getProcedimentos().get(0));
            for(Procedimento procedimento : receita.getProcedimentos()){
                procedimentoDAO.inserir(procedimento);
            }
            sucesso = true;
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sucesso;
    }

    @Override
    public boolean excluir(long id) throws PersistenciaException {
        boolean sucesso = false;
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql = " DELETE FROM imagem WHERE seq_imagem = ("
                     + " SELECT seq_imagem FROM receita WHERE "
                     + " nro_seq_receita = ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setLong(1, id);
            statement.execute();
            sucesso = true;
        }catch(Exception e){
            e.printStackTrace();
            throw new PersistenciaException(e.getMessage(), e);
        }
        return sucesso;
    }

    @Override
    public Receita consultarPorId(long id) throws PersistenciaException {
        Receita receita = new Receita();
        try{
            IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
            IIngredienteDAO ingredienteDAO = new IngredienteDAO();
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT A.nro_seq_receita, nom_login, seq_imagem, encode(arq_imagem,'base64') AS arq_imagem, nom_receita,  " +
                "       des_receita, dat_publicacao, idt_tendencia, qtd_tempo,  " +
                "       qtd_rendimento, COALESCE(vlr, 0) as vlr " +
                "  FROM receita AS A " +
                "  NATURAL JOIN imagem AS B " +
                "  LEFT JOIN ( " +
                "	SELECT nro_seq_receita, soma::real/qtd::real as vlr " +
                "	FROM ( " +
                "		SELECT A.nro_seq_receita, COALESCE(B.qtd,0) AS qtd, SUM(COALESCE(vlr_avaliacao,0)) as soma " +
                "		FROM avaliacao AS A " +
                "		LEFT JOIN ( " +
                "			SELECT nro_seq_receita, COUNT(*) as qtd  " +
                "			FROM avaliacao  " +
                "			GROUP BY 1 " +
                "		) as B " +
                "		ON A.nro_seq_receita = B.nro_seq_receita " +
                "		GROUP BY 1, 2 " +
                "	) as A " +
                "  ) AS C " +
                "ON A.nro_seq_receita = C.nro_seq_receita " +
                "WHERE A.nro_seq_receita = ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
             statement.setLong(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {                
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                receita.setAutor(usuarioDAO.consultarPorNome(resultSet.getString("nom_login")));
                receita.setSeq_imagem(resultSet.getLong("seq_imagem"));
                receita.setNom_receita(resultSet.getString("nom_receita"));
                receita.setDes_receita(resultSet.getString("des_receita"));
                receita.setDat_publicacao(resultSet.getDate("dat_publicacao"));
                receita.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                receita.setQtd_tempo(resultSet.getInt("qtd_tempo"));
                receita.setQtd_rendimento(resultSet.getString("qtd_rendimento"));
                receita.setImagem(resultSet.getString("arq_imagem"));
                receita.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
                receita.setNota(resultSet.getFloat("vlr"));
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
            String sql ="SELECT nro_seq_receita, nom_login, seq_imagem, encode(arq_imagem,'base64') AS arq_imagem, nom_receita, " +
                        "       des_receita, dat_publicacao, idt_tendencia, qtd_tempo, " +
                        "       qtd_rendimento, vlr" +
                        "  FROM receita" +
                        "  NATURAL JOIN (" +
                        "	SELECT nro_seq_receita, soma::real/qtd::real as vlr" +
                        "	FROM (" +
                        "		SELECT nro_seq_receita, tab.qtd, SUM(vlr_avaliacao) as soma" +
                        "		FROM avaliacao " +
                        "		NATURAL JOIN (" +
                        "			SELECT nro_seq_receita, COUNT(*) as qtd " +
                        "			FROM avaliacao " +
                        "			GROUP BY 1" +
                        "		) as tab" +
                        "		GROUP BY 1, 2" +
                        "	) as A" +
                        "  ) AS b;";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Receita receita = new Receita();
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                receita.setAutor(usuarioDAO.consultarPorNome(resultSet.getString("nom_login")));
                receita.setSeq_imagem(resultSet.getLong("seq_imagem"));
                receita.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
                receita.setNom_receita(resultSet.getString("nom_receita"));
                receita.setDes_receita(resultSet.getString("des_receita"));
                receita.setDat_publicacao(resultSet.getDate("dat_publicacao"));
                receita.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                receita.setQtd_tempo(resultSet.getInt("qtd_tempo"));
                receita.setQtd_rendimento(resultSet.getString("qtd_rendimento"));
                receita.setImagem(resultSet.getString("arq_imagem"));
                receita.setNota(resultSet.getFloat("vlr"));
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

    @Override
    public ArrayList<Receita> buscar(String search, String idt_tendencia) throws PersistenciaException {
        IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        IIngredienteDAO ingredienteDAO = new IngredienteDAO();
        ArrayList<Receita> receitas = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String[] listaParametros = search.split(",");
            String parametros = "SELECT '%?%' as parameter";
            for(int i = 1; i < listaParametros.length; i++){
                parametros += "UNION SELECT'%?%'";
            }
            String sql ="SELECT a.*, encode(c.arq_imagem, 'base64') AS arq_imagem, b.qtd" +
                        "FROM receita AS a" +
                        "NATURAL JOIN (" +
                        "	SELECT a.nro_seq_receita, a.qtd+b.qtd+c.qtd+d.qtd as qtd" +
                        "	FROM (" +
                        "		SELECT nro_seq_receita, COUNT(*) as QTD" +
                        "		FROM receita AS a" +
                        "		CROSS JOIN (" +
                        parametros +
                        "		) AS b" +
                        "		WHERE a.nom_receita LIKE b.parameter" +
                        "		GROUP BY 1" +
                        "	) as a" +
                        "	NATURAL JOIN (" +
                        "		SELECT nro_seq_receita, COUNT(*) as QTD" +
                        "		FROM receita AS a" +
                        "		CROSS JOIN (" +
                        parametros +
                        "		) AS b" +
                        "		WHERE a.des_receita LIKE b.parameter" +
                        "		GROUP BY 1" +
                        "	) as b" +
                        "	NATURAL JOIN (" +
                        "		SELECT nro_seq_receita, COUNT(*) as QTD" +
                        "		FROM ingrediente AS a" +
                        "		CROSS JOIN (" +
                        parametros +
                        "		) AS b" +
                        "		WHERE a.nom_ingrediente LIKE b.parameter" +
                        "		GROUP BY 1" +
                        "	) as C" +
                        "	NATURAL JOIN (" +
                        "		SELECT nro_seq_receita, COUNT(*) as QTD" +
                        "		FROM procedimento AS a" +
                        "		CROSS JOIN (" +
                        parametros +
                        "		) AS b" +
                        "		WHERE a.des_procedimento LIKE b.parameter" +
                        "		GROUP BY 1" +
                        "	) as D" +
                        ") as c" +
                        "NATURAL JOIN imagem AS c" +
                        "  NATURAL JOIN (" +
                        "	SELECT nro_seq_receita, soma::real/qtd::real as vlr" +
                        "	FROM (" +
                        "		SELECT nro_seq_receita, tab.qtd, SUM(vlr_avaliacao) as soma" +
                        "		FROM avaliacao " +
                        "		NATURAL JOIN (" +
                        "			SELECT nro_seq_receita, COUNT(*) as qtd " +
                        "			FROM avaliacao " +
                        "			GROUP BY 1" +
                        "		) as tab" +
                        "		GROUP BY 1, 2" +
                        "	) as a" +
                        "  ) AS d" +
                        "WHERE idt_tendencia = ?" +
                        "ORDER BY b.qtd;";
            PreparedStatement statement = connection.prepareStatement(sql);
            int index = 1;
            for(int i = 0; i < 4; i++){
                for(String atual : listaParametros){
                    statement.setString(index, atual);
                    index++;
                }
            }
            statement.setString(index, idt_tendencia);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Receita receita = new Receita();
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                receita.setAutor(usuarioDAO.consultarPorNome(resultSet.getString("nom_login")));
                receita.setSeq_imagem(resultSet.getLong("seq_imagem"));
                receita.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
                receita.setNom_receita(resultSet.getString("nom_receita"));
                receita.setDes_receita(resultSet.getString("des_receita"));
                receita.setDat_publicacao(resultSet.getDate("dat_publiacao"));
                receita.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                receita.setQtd_tempo(resultSet.getInt("qtd_tempo"));
                receita.setQtd_rendimento(resultSet.getString("qtd_rendimento"));
                receita.setImagem(resultSet.getString("arq_imagem"));
                receita.setNota(resultSet.getFloat("vlr"));
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

    @Override
    public ArrayList<Receita> recomendados(String nom_login) throws PersistenciaException {
        IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        IIngredienteDAO ingredienteDAO = new IngredienteDAO();
        ArrayList<Receita> receitas = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT nro_seq_receita, nom_login, seq_imagem, encode(arq_imagem,'base64') AS 'arq_imagem', nom_receita, " +
                        "       des_receita, dat_publicacao, a.idt_tendencia, qtd_tempo, " +
                        "       qtd_rendimento, vlr" +
                        "  FROM receita AS a" +
                        "  NATURAL JOIN IMAGEM" +
                        "  NATURAL JOIN (" +
                        "	SELECT nro_seq_receita, soma::real/qtd::real as vlr" +
                        "	FROM (" +
                        "		SELECT nro_seq_receita, tab.qtd, SUM(vlr_avaliacao) as soma" +
                        "		FROM avaliacao " +
                        "		NATURAL JOIN (" +
                        "			SELECT nro_seq_receita, COUNT(*) as qtd " +
                        "			FROM avaliacao " +
                        "			GROUP BY 1" +
                        "		) as tab" +
                        "		GROUP BY 1, 2" +
                        "	) as a" +
                        "  ) AS b" +
                        "  CROSS JOIN usuario AS c" +
                        "  WHERE c.nom_login = ?" +
                        "  ORDER BY 6 DESC, 10 DESC;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom_login);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Receita receita = new Receita();
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                receita.setAutor(usuarioDAO.consultarPorNome(resultSet.getString("nom_login")));
                receita.setSeq_imagem(resultSet.getLong("seq_imagem"));
                receita.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
                receita.setNom_receita(resultSet.getString("nom_receita"));
                receita.setDes_receita(resultSet.getString("des_receita"));
                receita.setDat_publicacao(resultSet.getDate("dat_publicacao"));
                receita.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                receita.setQtd_tempo(resultSet.getInt("qtd_tempo"));
                receita.setQtd_rendimento(resultSet.getString("qtd_rendimento"));
                receita.setImagem(resultSet.getString("arq_imagem"));
                receita.setNota(resultSet.getFloat("vlr"));
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

    @Override
    public ArrayList<Receita> listarPorUsuario(String nom_login) throws PersistenciaException {
        IProcedimentoDAO procedimentoDAO = new ProcedimentoDAO();
        IIngredienteDAO ingredienteDAO = new IngredienteDAO();
        ArrayList<Receita> receitas = new ArrayList<>();
        try{
            Connection connection = JDBCConnectionManager.getInstance().getConnection();
            String sql ="SELECT nro_seq_receita, nom_login, encode(arq_imagem,'base64') as arq_imagem, nom_receita, " +
                        "       des_receita, dat_publicacao, idt_tendencia, qtd_tempo, " +
                        "       qtd_rendimento, vlr " +
                        "  FROM receita AS a" +
                        "  NATURAL JOIN imagem b " +
                        "  NATURAL JOIN (" +
                        "	SELECT nro_seq_receita, soma::real/qtd::real as vlr " +
                        "	FROM (" +
                        "		SELECT nro_seq_receita, tab.qtd, SUM(vlr_avaliacao) as soma " +
                        "		FROM avaliacao " +
                        "		NATURAL JOIN (" + 
                        "			SELECT nro_seq_receita, COUNT(*) as qtd " +
                        "			FROM avaliacao " +
                        "			GROUP BY 1" +
                        "		) as tab" +
                        "		GROUP BY 1, 2" +
                        "	) as a" +
                        "  ) AS c" +
                        "  NATURAL JOIN usuario AS d" +
                        " WHERE nom_login = ?;";
            
            
            
            sql= "SELECT A.nro_seq_receita, nom_login, encode(arq_imagem,'base64') as arq_imagem, nom_receita, des_receita, dat_publicacao, idt_tendencia, qtd_tempo, qtd_rendimento,vlr \n" +
"	FROM receita AS a NATURAL JOIN imagem b LEFT JOIN (SELECT nro_seq_receita, soma::real/qtd::real as vlr\n" +
"			FROM ( SELECT nro_seq_receita, tab.qtd, SUM(vlr_avaliacao) as soma \n" +
"				FROM avaliacao NATURAL JOIN ( SELECT nro_seq_receita, COUNT(*) as qtd \n" +
"					FROM avaliacao GROUP BY 1 ) as tab GROUP BY 1, 2) as a) AS c ON A.nro_seq_receita=C.nro_seq_receita WHERE nom_login = ?;";
            
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, nom_login);
            ResultSet resultSet = statement.executeQuery();
            
            while(resultSet.next()) {
                Receita receita = new Receita();
                IUsuarioDAO usuarioDAO = new UsuarioDAO();
                receita.setAutor(usuarioDAO.consultarPorNome(resultSet.getString("nom_login")));
                //receita.setSeq_imagem(resultSet.getLong("seq_imagem"));
                receita.setNro_seq_receita(resultSet.getLong("nro_seq_receita"));
                receita.setNom_receita(resultSet.getString("nom_receita"));
                receita.setDes_receita(resultSet.getString("des_receita"));
                receita.setDat_publicacao(resultSet.getDate("dat_publicacao"));
                receita.setIdt_tendencia(resultSet.getString("idt_tendencia"));
                receita.setQtd_tempo(resultSet.getInt("qtd_tempo"));
                receita.setQtd_rendimento(resultSet.getString("qtd_rendimento"));
                receita.setImagem(resultSet.getString("arq_imagem"));
                receita.setNota(resultSet.getFloat("vlr"));
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
