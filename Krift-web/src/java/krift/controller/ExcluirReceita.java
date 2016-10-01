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
public class ExcluirReceita {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        String host = "localhost";
        
        int port = 2223;
        
        try {
            ArrayList<Receita> receitas = null;     
            
            String tendencia = request.getParameter("tendencia");            
            String busca = request.getParameter("busca"); 
            
            IManterReceita manter = new stubManterReceita(host,port);     
            
            receitas = manter.buscar(busca, tendencia);
            
            if(receitas!=null){
                request.setAttribute("receitas", receitas);
                jsp = "/resultados.jsp";
            }else{
                jsp = "/index.jsp";
            }    
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/index.jsp";
        }
        
        return jsp;        
    }
}    

