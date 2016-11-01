/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import krift.proxy.stubManterReceita;
import krift.proxy.stubManterUsuario;

/**
 *
 * @author Igor
 */
public class Login {

    public static String execute(HttpServletRequest request) {
        String jsp = "login.jsp";
        

       

        try {
            Usuario user = null;
            String nomeUsuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");

            IManterUsuario manter = new stubManterUsuario();
            IManterReceita manter2 = new stubManterReceita();

            if (nomeUsuario == null || nomeUsuario.equals("") || senha == null || senha.equals("")) {
                jsp = "/login.jsp";
            } else if (manter.logar(nomeUsuario, senha)) {
                user = manter.buscar(nomeUsuario);
                request.setAttribute("receitasRecomendadas", manter2.listarReceitasRecomendadas(nomeUsuario));

                if (user == null) {
                    System.out.println("USER NULO");
                }
                jsp = "/index.jsp";
                request.getSession().setAttribute("logado", user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/login.jsp";
        }

        return jsp;
    }
}
