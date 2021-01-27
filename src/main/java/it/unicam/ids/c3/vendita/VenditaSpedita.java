package it.unicam.ids.c3.vendita;

import javax.persistence.*;
import java.util.List;

@Entity
public class VenditaSpedita extends Vendita{

    @Enumerated(value = EnumType.STRING)
    private StatoConsegna statoConsegna;
    private String codiceRitiro;
    @Enumerated(value = EnumType.STRING)
    private LuogoDiRitiro luogoDiRitiro;
    private String indirizzoDiDomicilio;

    public VenditaSpedita(double prezzo, List<MerceVendita> listaMerceVendita) {
        super(prezzo, listaMerceVendita);
        this.statoConsegna = StatoConsegna.IN_ATTESA_DI_RITIRO;
    }

    public VenditaSpedita(double prezzo, List<MerceVendita> listMerceVendita, String indirizzoDiDomicilio){
        super(prezzo,listMerceVendita);
        this.indirizzoDiDomicilio = indirizzoDiDomicilio;
        this.statoConsegna = StatoConsegna.IN_ATTESA_DI_RITIRO;
    }

    public VenditaSpedita() {

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

}
