/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.proxy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import krift.common.model.domain.Usuario;
import krift.common.model.services.IManterUsuario;
import krift.common.util.AbstractInOut;
import krift.common.util.Request;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterUsuario implements IManterUsuario{

    private Socket socket;
    private String host = "localhost";
    private int port = 2223;

    public stubManterUsuario(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public boolean logar(String nom_login, String Senha) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.LOGAR);
            out.writeObject(nom_login);
            out.writeObject(Senha);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;            
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean cadastrar(Usuario usuario) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.CADASTRAR);
            out.writeObject(usuario);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public boolean alterar(Usuario usuario) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.CADASTRAR);
            out.writeObject(usuario);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public Usuario buscar(String nom_login) throws PersistenciaException, NegocioException {
        
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.LOGAR);
            out.writeObject(nom_login);
            out.flush();
            
            Usuario resposta = (Usuario) in.readObject();   
            
            return resposta;            
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public ArrayList<Usuario> listar() throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            out.writeObject(Request.LISTAR_USUARIOS);
            out.flush();
            ArrayList<Usuario> resposta = (ArrayList<Usuario>) in.readObject();
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }
    
}
