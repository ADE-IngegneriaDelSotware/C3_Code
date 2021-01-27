package it.unicam.ids.c3.merce;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Promozione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)private Long id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private double percentualeSconto;
    private boolean disponibilita;
    private double prezzoPromozione;

    public Promozione(LocalDate dataInizio, LocalDate dataFine, double percentualeSconto) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.percentualeSconto = percentualeSconto;
        this.disponibilita = true;
    }

    public Promozione(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public Promozione() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public double getPercentualeSconto() {
        return percentualeSconto;
    }

    public void setPercentualeSconto(double percentualeSconto) {
        this.percentualeSconto = percentualeSconto;
    }

    public boolean isDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(boolean disponibilita) {
        this.disponibilita = disponibilita;
    }

    public double getPrezzoPromozione() {
        return prezzoPromozione;
    }

    public void setPrezzoPromozione(double prezzoPromozione) {
        this.prezzoPromozione = prezzoPromozione;
    }
}
