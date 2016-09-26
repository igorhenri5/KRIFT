/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao;

import java.util.ArrayList;
import krift.common.model.domain.Receita;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IHistoricoDAO {

    void inserir(String nom_login, long nro_seq_receita) throws PersistenciaException;

    ArrayList<Receita> listarHistorico(String nom_login) throws PersistenciaException;
    
}
