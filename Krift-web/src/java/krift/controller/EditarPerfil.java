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
        
        try {
            
            Usuario user = new Usuario();            
            
            String nomePerfil = request.getParameter("nome");
            String tendencia = request.getParameter("tendencia");
            String imagem = request.getParameter("img64");
            String sobre = request.getParameter("sobre");    
                    
            
            System.out.println(nomePerfil);
            System.out.println(sobre);
            System.out.println(tendencia);
            System.out.println(imagem);
            
            IManterUsuario manter = new stubManterUsuario();
            
            if(tendencia.equals("")||tendencia==null||nomePerfil.equals("")||nomePerfil==null){
                jsp = "/editarPerfil.jsp";
            }else{
                user = manter.buscar(((Usuario) request.getSession().getAttribute("logado")).getNom_login());
                user.setNom_perfil_usuario(nomePerfil);
                user.setIdt_tendencia(tendencia);
                user.setDes_usuario(sobre);
                user.setImagem(imagem.substring(22));
                manter.alterar(user);                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }

        return jsp;
    }
}
