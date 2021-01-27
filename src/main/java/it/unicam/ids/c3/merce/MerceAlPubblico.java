package it.unicam.ids.c3.merce;

import javax.persistence.*;

@Entity
public class MerceAlPubblico {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double prezzo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "merce_fk", referencedColumnName = "id")
    private Merce merce;
    private double sconto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promozione_fk", referencedColumnName = "id")
    private Promozione promozione;

    public MerceAlPubblico(double prezzo, Merce merce) {
        this.prezzo = prezzo;
        this.merce = merce;
    }

    public MerceAlPubblico(double prezzo, Merce merce, int sconto) {
        this.prezzo = prezzo;
        this.merce = merce;
        this.sconto = sconto;
    }

    public MerceAlPubblico() {

    }

    public Promozione getPromozione() {
        return promozione;
    }

    public void setPromozione(Promozione promozione) {
        this.promozione = promozione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Merce getMerce() {
        return merce;
    }

    public void setMerce(Merce merce) {
        this.merce = merce;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    @Override
    public String toString() {
        return prezzo +
                ", " + merce +
                ", " + sconto;
    }
}
