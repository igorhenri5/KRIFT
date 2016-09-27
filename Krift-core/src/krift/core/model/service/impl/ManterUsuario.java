/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core.model.service.impl;

import java.util.ArrayList;
import krift.common.model.domain.Usuario;
import krift.common.model.services.IManterUsuario;
import krift.core.model.dao.IUsuarioDAO;
import krift.core.model.dao.impl.UsuarioDAO;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Aluno
 */
public class ManterUsuario implements IManterUsuario{

    @Override
    public boolean logar(String nom_login, String Senha) throws PersistenciaException, NegocioException {
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        if(nom_login == null || Senha == null){
            throw new NegocioException("É necessario informar tanto o nome de usuario quanto senha");
        }
        return usuarioDAO.login(nom_login, Senha);
    }

    @Override
    public boolean cadastrar(Usuario usuario) throws PersistenciaException, NegocioException {
        
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        
        
        if(usuario.getSenha()== null ||
           usuario.getNom_login()== null ||
           usuario.getEmail()== null){
            throw new NegocioException("Nome, senha e email são obrigatorios");
        }
        
        
        return usuarioDAO.inserir(usuario);
    }

    @Override
    public boolean alterar(Usuario usuario) throws PersistenciaException, NegocioException {
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.atualizar(usuario);
    }

    @Override
    public Usuario buscar(String nom_login) throws PersistenciaException, NegocioException {
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        if(nom_login == null){
            throw new NegocioException("É necessario informar o nome");
        }
        return usuarioDAO.consultarPorNome(nom_login);
    }

    @Override
    public ArrayList<Usuario> listar() throws PersistenciaException, NegocioException {
        IUsuarioDAO usuarioDAO = new UsuarioDAO();
        return usuarioDAO.listarTodos();
    }
}
