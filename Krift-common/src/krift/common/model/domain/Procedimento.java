/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.domain;

/**
 *
 * @author Nome
 */
public class Procedimento {
    private long nro_seq_receita;
    private long nro_seq_procedimento;
    private String des_procedimento;

    public Procedimento() {
    }

    public Procedimento(long nro_seq_receita, long seq_seq_procedimento, String des_procedimento) {
        this.nro_seq_receita = nro_seq_receita;
        this.nro_seq_procedimento = seq_seq_procedimento;
        this.des_procedimento = des_procedimento;
    }

    public long getNro_seq_receita() {
        return nro_seq_receita;
    }

    public void setNro_seq_receita(long nro_seq_receita) {
        this.nro_seq_receita = nro_seq_receita;
    }

    public long getNro_seq_procedimento() {
        return nro_seq_procedimento;
    }

    public void setNro_seq_procedimento(long nro_seq_procedimento) {
        this.nro_seq_procedimento = nro_seq_procedimento;
    }

    public String getDes_procedimento() {
        return des_procedimento;
    }

    public void setDes_procedimento(String des_procedimento) {
        this.des_procedimento = des_procedimento;
    }
    
}
