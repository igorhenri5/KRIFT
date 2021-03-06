/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.service.impl;

import java.util.ArrayList;
import krift.common.model.domain.Receita;
import krift.common.model.services.IManterFavoritos;
import krift.core.model.dao.IFavoritoDAO;
import krift.core.model.dao.impl.FavoritoDAO;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public class ManterFavoritos implements IManterFavoritos{

    @Override
    public boolean favoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        IFavoritoDAO favoritoDAO = new FavoritoDAO();
        return favoritoDAO.inserir(nom_login, nro_seq_receita);
    }

    @Override
    public boolean desfavoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        IFavoritoDAO favoritoDAO = new FavoritoDAO();
        return favoritoDAO.excluir(nom_login, nro_seq_receita);
    }

    @Override
    public ArrayList<Receita> listarFavoritos(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        IFavoritoDAO favoritoDAO = new FavoritoDAO();
        return favoritoDAO.listarFavoritos(nom_login);
    }
}
