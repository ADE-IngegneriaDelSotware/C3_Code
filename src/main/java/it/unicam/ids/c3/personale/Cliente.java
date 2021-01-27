package it.unicam.ids.c3.personale;


import it.unicam.ids.c3.negozio.Carta;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ruolo_fk", referencedColumnName = "id")
    private Ruolo ruolo;

    @OneToMany(mappedBy = "cliente")
    private List<Carta> carte;

    public Cliente(String nome, String cognome, String codiceFiscale, String email,String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.password = password;
    }

    public Cliente() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public List<Carta> getCarte() {
        return carte;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + ruolo +
                ", carte=" + carte +
                '}';
    }
}
