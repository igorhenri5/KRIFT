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
public class Cadastrar {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "cadastro.jsp";
        
        
       
        
        try {
            
            Usuario user = new Usuario();            
            
            String email = request.getParameter("email");
            String nomeUsuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
            String confirmacaoSenha = request.getParameter("confirmacao");
            
            IManterUsuario manter = new stubManterUsuario();
            
            if(email.equals("")||email==null||nomeUsuario.equals("")||nomeUsuario==null||senha.equals("")||senha==null||!(confirmacaoSenha.equals(senha))){
                jsp = "/cadastro.jsp";
            }else{
                user.setEmail(email);
                user.setNom_login(nomeUsuario);
                user.setSenha(senha);         
                user.setIdt_tendencia("SEM");
                request.getSession().setAttribute("SessaoTendencia","SEM");
                
                if(manter.cadastrar(user)){      
                    jsp = "/index.jsp";
                    request.getSession().setAttribute("logado", manter.buscar(nomeUsuario));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/cadastro.jsp";
        }
        
        return jsp;        
    }
}    

