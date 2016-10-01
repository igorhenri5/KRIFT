/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.*;
import krift.common.model.services.IManterUsuario;
import krift.proxy.stubManterUsuario;

/**
 *
 * @author Igor
 */
public class EditarPerfil {

    public static String execute(HttpServletRequest request) {

        String jsp = "index.jsp";
        String host = "localhost";

        int port = 2223;
        try {
            
            Usuario user = new Usuario();            
            
            String nomePerfil = request.getParameter("nome");
            String tendencia = request.getParameter("tendencia");
            String imagem = request.getParameter("imagem");
            String sobre = request.getParameter("sobre");    
                    
            IManterUsuario manter = new stubManterUsuario(host,port);
            
            if(tendencia.equals("")||tendencia==null||nomePerfil.equals("")||nomePerfil==null||sobre.equals("")||sobre==null){
                jsp = "/editarPerfil.jsp";
            }else{
                user.setNom_perfil_usuario(nomePerfil);
                user.setIdt_tendencia(tendencia);
                user.setDes_usuario(sobre);
                user.setImagem(imagem.getBytes());
                manter.alterar(user);                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }

        return jsp;
    }
}
