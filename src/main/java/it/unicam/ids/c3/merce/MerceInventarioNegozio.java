package it.unicam.ids.c3.merce;

import javax.persistence.*;

@Entity
public class MerceInventarioNegozio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double quantita;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merceAlPubblico_fk", referencedColumnName = "id")
    private MerceAlPubblico merceAlPubblico;

    public MerceInventarioNegozio(double quantita, MerceAlPubblico merceAlPubblico) {
        this.quantita=quantita;
        this.merceAlPubblico=merceAlPubblico;
    }

    public MerceInventarioNegozio() {

    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public MerceAlPubblico getMerceAlPubblico() {
        return merceAlPubblico;
    }

    public void setMerceAlPubblico(MerceAlPubblico merceAlPubblico) {
        this.merceAlPubblico = merceAlPubblico;
    }

    @Override
    public String toString() {
        return "MerceInventarioNegozioClass{" +
                "quantita=" + quantita +
                ", merceAlPubblico=" + merceAlPubblico +
                '}';
    }
}

