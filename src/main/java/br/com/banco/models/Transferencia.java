package br.com.banco.models;

import java.sql.Timestamp;
import java.text.DecimalFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "transferencia")
public class Transferencia {
    private static final DecimalFormat df = new DecimalFormat("####.00");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_transferencia")
    private Timestamp dataTransferencia;
    @Column(nullable = false, scale = 2, precision = 20)
    private float valor;
    @Column(nullable = false, length = 15)
    private String tipo;
    @Column(nullable = false, length = 50, name = "nome_operador_transacao")
    private String nomeOperadorTransacao;
    @OneToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Conta getConta() {
        return this.conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
    public String getValor() {
        String formated = df.format(this.valor).replaceAll(",", ".");
        return formated;
    }
}