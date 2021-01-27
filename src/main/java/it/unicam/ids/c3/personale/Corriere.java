package it.unicam.ids.c3.personale;

import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.VenditaSpedita;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
public class Corriere extends Ruolo{

    private String nomeDitta;
    private String indirizzo;
    private String p_iva;
    private boolean disponiblita;

    @OneToMany(mappedBy = "corriere")
    private List<VenditaSpedita> vendite;

    public Corriere(RuoloSistema ruolo, String nomeDitta, String indirizzo, String p_iva) {
        super(ruolo);
        this.nomeDitta = nomeDitta;
        this.indirizzo = indirizzo;
        this.p_iva = p_iva;
        this.vendite = new ArrayList<>();
    }

    public Corriere (){}

    public String getNomeDitta() {
        return nomeDitta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getP_iva(){
        return p_iva;
    }

    public boolean isDisponiblita() {
        return disponiblita;
    }

    public List<VenditaSpedita> getVendite() {
        return vendite;
    }

    public void addMerceDaSpedire(VenditaSpedita vs){
        this.vendite.add(vs);
    }

    public List<VenditaSpedita> getVenditePerStato(StatoConsegna sc) {
        List<VenditaSpedita> venditePerTipo = new ArrayList<>();
        Iterator<VenditaSpedita> iterator = getVendite().iterator();
        while (iterator.hasNext()){
            VenditaSpedita vs = iterator.next();
            if(vs.getStatoConsegna().equals(sc)){
                venditePerTipo.add(vs);
            }
        }
        return venditePerTipo;
    }

    public void setDisponiblita(boolean disponiblita){
        this.disponiblita = disponiblita;
    }



}
