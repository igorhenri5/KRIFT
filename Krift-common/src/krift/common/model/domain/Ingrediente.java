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
public class Ingrediente implements Serializable{
    private long nro_seq_receita;
    private long nro_seq_ingrediente;
    private String des_quantidade;
    private String nom_ingrediente;

    public Ingrediente() {
    }

    public Ingrediente(long nro_seq_receita, long seq_seq_ingrediente, String des_quantidade, String nom_ingrediente) {
        this.nro_seq_receita = nro_seq_receita;
        this.nro_seq_ingrediente = seq_seq_ingrediente;
        this.des_quantidade = des_quantidade;
        this.nom_ingrediente = nom_ingrediente;
    }

    public long getNro_seq_receita() {
        return nro_seq_receita;
    }

    public void setNro_seq_receita(long nro_seq_receita) {
        this.nro_seq_receita = nro_seq_receita;
    }

    public long getNro_seq_ingrediente() {
        return nro_seq_ingrediente;
    }

    public void setNro_seq_ingrediente(long nro_seq_ingrediente) {
        this.nro_seq_ingrediente = nro_seq_ingrediente;
    }

    public String getDes_quantidade() {
        return des_quantidade;
    }

    public void setDes_quantidade(String des_quantidade) {
        this.des_quantidade = des_quantidade;
    }

    public String getNom_ingrediente() {
        return nom_ingrediente;
    }

    public void setNom_ingrediente(String nom_ingrediente) {
        this.nom_ingrediente = nom_ingrediente;
    }
    
}
