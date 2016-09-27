/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import krift.proxy.stubManterUsuario;

/**
 *
 * @author Igor
 */
public class Login {

    public static String execute(HttpServletRequest request) {
        String jsp = "index.jsp";
        String host = "localhost";

        int port = 2223;

        try {
            Usuario user = new Usuario();            

            String nomeUsuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");            
            
            IManterUsuario manter = new stubManterUsuario(host,port);
            
            if(nomeUsuario.equals("")||nomeUsuario==null||senha.equals("")||senha==null){
                jsp = "/login.jsp";
            }else{            
                if (manter.logar(nomeUsuario, senha)) {        
                    jsp = "/index.jsp";
                    request.getSession().setAttribute("usuario", user);
                    request.getSession().setAttribute("logado", true);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/login.jsp";
        }

        return jsp;
    }
}
