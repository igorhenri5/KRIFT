/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.dao;

import java.util.ArrayList;
import krift.common.model.domain.Usuario;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Nome
 */
public interface IUsuarioDAO{
    String inserir(Usuario obj) throws PersistenciaException;
    void atualizar(Usuario obj) throws PersistenciaException;
    Usuario consultarPorNome(String name) throws PersistenciaException;
    ArrayList<Usuario> listarTodos() throws PersistenciaException;
}
