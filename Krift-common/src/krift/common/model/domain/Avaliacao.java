/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.domain;

import java.io.Serializable;

/**
 *
 * @author Nome
 */
public class Avaliacao implements Serializable{
    private long nro_seq_receita;
    private String nom_login;
    private int vlr_avaliacao;

    public Avaliacao() {
    }

    public Avaliacao(long nro_seq_receita, String nom_login, int vlr_avaliacao) {
        this.nro_seq_receita = nro_seq_receita;
        this.nom_login = nom_login;
        this.vlr_avaliacao = vlr_avaliacao;
    }

    public long getNro_seq_receita() {
        return nro_seq_receita;
    }

    public void setNro_seq_receita(long nro_seq_receita) {
        this.nro_seq_receita = nro_seq_receita;
    }

    public String getNom_login() {
        return nom_login;
    }

    public void setNom_login(String nom_login) {
        this.nom_login = nom_login;
    }

    public int getVlr_avaliacao() {
        return vlr_avaliacao;
    }

    public void setVlr_avaliacao(int vlr_avaliacao) {
        this.vlr_avaliacao = vlr_avaliacao;
    }
    
    
}
