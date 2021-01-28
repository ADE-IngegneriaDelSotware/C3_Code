package it.unicam.ids.c3.vendita;


import it.unicam.ids.c3.personale.Cliente;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Vendita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private LocalDate data;
    private double prezzo;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name="vendita_fk", referencedColumnName = "id")
    private List<MerceVendita> listaMerceVendita;

    public Vendita(double prezzo, List<MerceVendita> listaMerceVendita) {
        this.data = LocalDate.now();
        this.prezzo = prezzo;
        this.listaMerceVendita=listaMerceVendita;
    }

    public Vendita() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public List<MerceVendita> getListaMerceVendita() {
        return listaMerceVendita;
    }

    public void setListaMerceVendita(List<MerceVendita> listaMerceVendita) {
        this.listaMerceVendita = listaMerceVendita;
    }
}