package it.unicam.ids.c3.merce;

import javax.persistence.*;

@Entity
public class Merce {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int ID;
    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private String descrizione;

    public Merce(String nome, Categoria categoria, String descrizione) {
        this.nome=nome;
        this.categoria=categoria;
        this.descrizione=descrizione;
    }

    public Merce() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return  ID +
                ", " + nome  +
                ", " + categoria +
                ", " + descrizione;
    }
}
