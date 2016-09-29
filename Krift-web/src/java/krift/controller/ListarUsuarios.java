/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import krift.proxy.*;

/**
 *
 * @author Igor
 */
public class ListarUsuarios {

    public static String execute(HttpServletRequest request) {

        String jsp = "index.jsp";
        String host = "localhost";

        int port = 2223;

        try {

            ArrayList<Usuario> user = null;

            IManterUsuario manter = new stubManterUsuario(host, port);

            user = manter.listar();
            
            if (user != null) {
                request.setAttribute("listaRanking", user);
                jsp = "/ranking.jsp";
            }else{
                jsp = "/index.jsp";
            }
            
            request.setAttribute("usuarios", user);

        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }

        return jsp;
    }
}
