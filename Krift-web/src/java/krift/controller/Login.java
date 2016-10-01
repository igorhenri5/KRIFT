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
        String host = "localhost";

        int port = 2223;

        try {
            Usuario user = null;
            String nomeUsuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");

            IManterUsuario manter = new stubManterUsuario(host, port);
            IManterReceita manter2 = new stubManterReceita(host, port);

            if (nomeUsuario == null || nomeUsuario.equals("") || senha == null || senha.equals("")) {
                jsp = "/login.jsp";
            } else if (manter.logar(nomeUsuario, senha)) {
                user = manter.buscar(nomeUsuario);
                request.setAttribute("receitasRecomendadas", manter2.listarReceitasRecomendadas(host));

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
