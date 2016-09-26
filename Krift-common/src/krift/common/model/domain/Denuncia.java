/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package krift.common.model.domain;

/**
 *
 * @author Aluno
 */
public class Denuncia {
    private long seq_denuncia;
    private long nro_seq_receita;
    private String des_comentario;

    public Denuncia() {
    }

    public Denuncia(long seq_denuncia, long seq_receita, String des_comentario) {
        this.seq_denuncia = seq_denuncia;
        this.nro_seq_receita = seq_receita;
        this.des_comentario = des_comentario;
    }

    public long getSeq_denuncia() {
        return seq_denuncia;
    }

    public void setSeq_denuncia(long seq_denuncia) {
        this.seq_denuncia = seq_denuncia;
    }

    public long getNro_seq_receita() {
        return nro_seq_receita;
    }

    public void setNro_seq_receita(long nro_seq_receita) {
        this.nro_seq_receita = nro_seq_receita;
    }

    public String getDes_comentario() {
        return des_comentario;
    }

    public void setDes_comentario(String des_comentario) {
        this.des_comentario = des_comentario;
    }

    
    
    
}
