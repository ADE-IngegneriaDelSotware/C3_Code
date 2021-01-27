package it.unicam.ids.c3.vendita;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class VenditaSpedita extends Vendita{

    private StatoConsegna statoConsegna;
    private String codiceRitiro;
    //private Negozio puntoDiRitiro;
    private LuogoDiRitiro luogoDiRitiro;
    private String indirizzoDiDomicilio;
    //private Corriere corriere;

    public VenditaSpedita(double prezzo, List<MerceVendita> listaMerceVendita, /*Negozio puntoDiRitiro, Corriere corriere,*/ LuogoDiRitiro trc) {
        super(prezzo, listaMerceVendita);
        //this.puntoDiRitiro = puntoDiRitiro;
        //this.corriere = corriere;
        this.luogoDiRitiro = trc;
        this.statoConsegna = StatoConsegna.IN_ATTESA_DI_RITIRO;
    }

    public VenditaSpedita(double prezzo, List<MerceVendita> listMerceVendita, String indirizzoDiDomicilio,/* Corriere corriere*/ LuogoDiRitiro trc){
        super(prezzo,listMerceVendita);
        this.indirizzoDiDomicilio = indirizzoDiDomicilio;
        //this.corriere = corriere;
        this.luogoDiRitiro = trc;
        this.statoConsegna = StatoConsegna.IN_ATTESA_DI_RITIRO;
    }
}
