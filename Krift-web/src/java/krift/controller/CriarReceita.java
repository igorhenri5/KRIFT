/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import krift.common.model.domain.*;
import krift.common.model.services.*;
import krift.proxy.*;

/**
 *
 * @author Igor
 */
public class CriarReceita {
    
    public static String execute(HttpServletRequest request) {
        
        String jsp = "index.jsp";
        
        try {
            
            Receita receita = new Receita();            
            
            String nome = request.getParameter("nome");
            String descricao = request.getParameter("descricao");
            String rendimento = request.getParameter("rendimento");
            String tempo = request.getParameter("tempo");
            String tendencia = request.getParameter("tendencia");
            String imagem = request.getParameter("img64");
            ArrayList<Ingrediente> ingredientes = new ArrayList<>();
            ArrayList<Procedimento> procedimentos = new ArrayList<>();
            
            for(int i = 0; i < request.getParameterValues("ingrediente").length &&
                    i < request.getParameterValues("quantidade").length; i++){
                Ingrediente atual = new Ingrediente();
                atual.setNom_ingrediente(request.getParameterValues("ingrediente")[i]);
                atual.setDes_quantidade(request.getParameterValues("quantidade")[i]);
                ingredientes.add(atual);
            }
            for(int i = 0; i < request.getParameterValues("passo").length; i++){
                Procedimento atual = new Procedimento();
                atual.setDes_procedimento(request.getParameterValues("passo")[i]);
                procedimentos.add(atual);
            }
            
            IManterReceita manter = new stubManterReceita();
            
            if (nome == null || ingredientes.size() ==0 || procedimentos.size() == 0 ||
                descricao == null || rendimento == null || 
                request.getSession().getAttribute("logado") == null){
                jsp = "/cadastrarReceita.jsp";
            }else{
                
                receita.setNom_receita(nome);
                receita.setAutor((Usuario)request.getSession().getAttribute("logado"));
                receita.setDes_receita(descricao);
                receita.setIdt_tendencia(tendencia);
                receita.setImagem(imagem.substring(22));
                receita.setIngredientes(ingredientes);
                receita.setQtd_rendimento(rendimento);
                receita.setProcedimentos(procedimentos);
                receita.setQtd_tempo(Integer.parseInt(tempo));
                receita.setNum_login(((Usuario)request.getSession().getAttribute("logado")).getNom_login());
                        
                if(manter.criar(receita)){      
                    jsp = "/index.jsp";
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/cadastrarReceita.jsp";
        }
        
        return jsp;        
    }
}    
