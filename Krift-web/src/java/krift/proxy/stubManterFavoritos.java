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
import krift.common.model.domain.*;
import krift.common.model.services.IManterFavoritos;
import krift.common.util.AbstractInOut;
import krift.common.util.Request;
import util.db.exception.NegocioException;
import util.db.exception.PersistenciaException;

/**
 *
 * @author Igor
 */
public class stubManterFavoritos implements IManterFavoritos{
    
    private Socket socket;
     private String host = "localhost";
    private int port = 2223;
    
    public stubManterFavoritos(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public boolean favoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.FAVORITAR);
            
            out.writeObject(nom_login);
            out.writeObject(nro_seq_receita);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public ArrayList<Receita> listarFavoritos(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.LISTAR_FAVORITOS);
            out.writeObject(nom_login);
            out.writeObject(nro_seq_receita);
            out.flush();
            
            ArrayList<Receita> resposta = (ArrayList) in.readObject();            
            return resposta;
        } catch (IOException | ClassNotFoundException ex) {
            return null;
        }
    }

    @Override
    public boolean desfavoritar(String nom_login, long nro_seq_receita) throws PersistenciaException, NegocioException {
        try {
            socket = new Socket(host, port);
            ObjectInputStream in = AbstractInOut.getObjectReader(socket);
            ObjectOutputStream out = AbstractInOut.getObjectWriter(socket);
            
            out.writeObject(Request.REMOVER_FAVORITO);
            out.writeObject(nom_login);
            out.writeObject(nro_seq_receita);
            out.flush();
            
            boolean resposta = in.readBoolean();            
            return resposta;
        } catch (IOException ex) {
            return false;
        }
    }
    
}
