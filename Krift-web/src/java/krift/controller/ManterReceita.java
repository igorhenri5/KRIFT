/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.Receita;
import krift.common.model.services.IManterReceita;
import krift.proxy.stubManterReceita;

/**
 *
 * @author Igor
 */
public class ManterReceita {

    public static String execute(HttpServletRequest request) {

        String jsp = "/manterReceita.jsp";
               
        try {            
            ArrayList<Receita> receitas = null;
            
            IManterReceita manter = new stubManterReceita();
            String nome = request.getParameter("nome");
            receitas = manter.listarReceitasPorUsuario(nome);
            request.setAttribute("receitas", receitas);
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }

        return jsp;
    }
}    

