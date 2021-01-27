package it.unicam.ids.c3.personale;


import javax.persistence.*;

@Entity
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String email;
    private String userName;
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ruolo_id", referencedColumnName = "id")
    private Ruolo ruolo;

    public Cliente(String nome, String cognome, String codiceFiscale, String email, String userName, String password) {
        this.nome = nome;
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public Cliente() {
    }

    public long getId() {
        return id;
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

    public String getUserName() {
        return userName;
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

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + ruolo +
                '}';
    }
}
