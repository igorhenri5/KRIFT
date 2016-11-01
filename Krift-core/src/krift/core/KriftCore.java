/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.core;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import krift.common.model.services.IAvaliarReceita;
import krift.common.model.services.IManterComentario;
import krift.common.model.services.IManterFavoritos;
import krift.common.model.services.IManterReceita;
import krift.common.model.services.IManterUsuario;
import krift.core.model.service.impl.AvaliarReceita;
import krift.core.model.service.impl.ManterComentario;
import krift.core.model.service.impl.ManterFavoritos;
import krift.core.model.service.impl.ManterReceita;
import krift.core.model.service.impl.ManterUsuario;

/**
 *
 * @author aluno
 */
public class KriftCore {

    public static void main(String args[]) throws IOException {
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        
        IAvaliarReceita avaliarReceita = new AvaliarReceita ();
        IManterComentario manterComentario = new ManterComentario();
        IManterFavoritos manterFavoritos = new ManterFavoritos ();
        IManterReceita manterReceita = new ManterReceita();
        IManterUsuario manterUsuario = new ManterUsuario ();
        
        IAvaliarReceita avaliarStub = (IAvaliarReceita)UnicastRemoteObject.exportObject(avaliarReceita, 0);
        IManterComentario comentarioStub=(IManterComentario)UnicastRemoteObject.exportObject(manterComentario, 0);
        IManterFavoritos favoritosStub=(IManterFavoritos)UnicastRemoteObject.exportObject(manterFavoritos, 0);
        IManterReceita receitaStub=(IManterReceita)UnicastRemoteObject.exportObject(manterReceita, 0);
        IManterUsuario usuarioStub=(IManterUsuario)UnicastRemoteObject.exportObject(manterUsuario, 0);
        
        Registry registry = LocateRegistry.createRegistry(2345);
        
        registry.rebind ("avaliar", avaliarStub);
        registry.rebind ("comentario", comentarioStub);
        registry.rebind ("favoritos", favoritosStub);
        registry.rebind ("receita", receitaStub);
        registry.rebind ("usuario", usuarioStub);
    }
}
