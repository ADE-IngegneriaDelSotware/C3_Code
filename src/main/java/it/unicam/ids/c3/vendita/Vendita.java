package it.unicam.ids.c3.vendita;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Vendita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    private LocalDate data;
    private double prezzo;
    @OneToMany(targetEntity = MerceVendita.class, cascade= CascadeType.ALL)
    @JoinColumn(name="vendita_fk", referencedColumnName = "id")
    private List<MerceVendita> listaMerceVendita;

    public Vendita(double prezzo, List<MerceVendita> listaMerceVendita) {
        this.data = LocalDate.now();
        this.prezzo = prezzo;
        this.listaMerceVendita=listaMerceVendita;
    }

    public Vendita() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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