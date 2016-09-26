/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.services;

import java.util.ArrayList;
import krift.common.model.domain.Usuario;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public interface IManterUsuario {
    public boolean logar(String nom_login, String Senha) throws PersistenciaException, NegocioException;
    public boolean cadastrar(Usuario usuario) throws PersistenciaException, NegocioException;
    public boolean alterar(Usuario usuario) throws PersistenciaException, NegocioException;
    public Usuario buscar(String nom_login) throws PersistenciaException, NegocioException;
    public ArrayList<Usuario> listar() throws PersistenciaException, NegocioException;
}
