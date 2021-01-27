package it.unicam.ids.c3.vendita;

import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.personale.Corriere;

import javax.persistence.*;
import java.util.List;

@Entity
public class VenditaSpedita extends Vendita{

    @Enumerated(value = EnumType.STRING)
    private StatoConsegna statoConsegna;
    private String codiceRitiro;

    @Transient
    private Negozio puntoDiRitiro;

    @Enumerated(value = EnumType.STRING)
    private LuogoDiRitiro luogoDiRitiro;
    private String indirizzoDiDomicilio;

    @ManyToOne
    @JoinColumn(name = "corriere_fk")
    private Corriere corriere;

    public VenditaSpedita(double prezzo, List<MerceVendita> listaMerceVendita, Negozio puntoDiRitiro, Corriere corriere, LuogoDiRitiro trc) {
        super(prezzo, listaMerceVendita);
        this.puntoDiRitiro = puntoDiRitiro;
        this.corriere = corriere;
        this.luogoDiRitiro = trc;
        this.statoConsegna = StatoConsegna.IN_ATTESA_DI_RITIRO;
    }

    public VenditaSpedita(double prezzo, List<MerceVendita> listMerceVendita, String indirizzoDiDomicilio, Corriere corriere, LuogoDiRitiro trc){
        super(prezzo,listMerceVendita);
        this.indirizzoDiDomicilio = indirizzoDiDomicilio;
        this.corriere = corriere;
        this.luogoDiRitiro = trc;
        this.statoConsegna = StatoConsegna.IN_ATTESA_DI_RITIRO;
    }

    public StatoConsegna getStatoConsegna() {
        return statoConsegna;
    }

    public void setStatoConsegna(StatoConsegna statoConsegna) {
        this.statoConsegna = statoConsegna;
    }

    public String getCodiceRitiro() {
        return codiceRitiro;
    }

    public void setCodiceRitiro(String codiceRitiro) {
        this.codiceRitiro = codiceRitiro;
    }

    public LuogoDiRitiro getLuogoDiRitiro() {
        return luogoDiRitiro;
    }

    public void setLuogoDiRitiro(LuogoDiRitiro luogoDiRitiro) {
        this.luogoDiRitiro = luogoDiRitiro;
    }

    public String getIndirizzoDiDomicilio() {
        return indirizzoDiDomicilio;
    }

    public void setIndirizzoDiDomicilio(String indirizzoDiDomicilio) {
        this.indirizzoDiDomicilio = indirizzoDiDomicilio;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void setCorriere(Corriere corriere) {
        this.corriere = corriere;
    }

    @Override
    public String toString() {
        return "VenditaSpedita{" +
                "statoConsegna=" + statoConsegna +
                ", codiceRitiro='" + codiceRitiro + '\'' +
                ", puntoDiRitiro=" + puntoDiRitiro +
                ", luogoDiRitiro=" + luogoDiRitiro +
                ", indirizzoDiDomicilio='" + indirizzoDiDomicilio + '\'' +
                ", corriere=" + corriere +
                '}';
    }
}
