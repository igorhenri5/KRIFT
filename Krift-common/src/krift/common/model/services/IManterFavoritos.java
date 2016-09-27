/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.services;

import java.util.ArrayList;
import krift.common.model.domain.Receita;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public interface IManterFavoritos {
    public boolean favoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException;
    public ArrayList<Receita> listarFavoritos(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException;
    public boolean desfavoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException;
}
