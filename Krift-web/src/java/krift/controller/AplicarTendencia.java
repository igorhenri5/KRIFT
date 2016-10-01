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
public class AplicarTendencia {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        String host = "localhost";
        
        int port = 2223;
        
        try {
            
            String tendencia = request.getParameter("SessaoTendencia");            
            
            if(tendencia.equals("ONV")||tendencia.equals("CRN")||tendencia.equals("VGT")||tendencia.equals("VGN")||tendencia.equals("SEM")){
                request.getSession().setAttribute("SessaoTendencia",tendencia);
                jsp = "/index.jsp";
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

