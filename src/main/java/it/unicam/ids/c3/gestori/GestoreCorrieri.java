package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.vendita.LuogoDiRitiro;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreCorrieri {

    private Corriere corriere;

    public void setCorriere(Corriere corriere){
        this.corriere = corriere;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void aggiornaStatoVendita(List<VenditaSpedita> list, StatoConsegna sc) {
        List<VenditaSpedita> lista = list;
        for(VenditaSpedita vs : lista){
            Iterator<VenditaSpedita> iterator = getCorriere().getVendite().iterator();
            while (iterator.hasNext()){
                VenditaSpedita vsIterata = iterator.next();
                if(vsIterata.equals(vs)){
                    vs.setStatoConsegna(sc);
                }
            }
        }
    }
    /************Consulta Inventario********************/
    public List<VenditaSpedita> getVenditeDaRitirare() {
        return getCorriere().getVenditePerStato(StatoConsegna.IN_ATTESA_DI_RITIRO);
    }

    public List<VenditaSpedita> getVenditeRitirate() {
        return getCorriere().getVenditePerStato(StatoConsegna.RITIRATO);
    }

    public List<VenditaSpedita> getVenditeConsegnate() {
        List<VenditaSpedita> cac = getCorriere().getVenditePerStato(StatoConsegna.CONSEGNATO_AL_CLIENTE);
        List<VenditaSpedita> caldr = getCorriere().getVenditePerStato(StatoConsegna.CONSEGNATO_AL_NEGOZIO);
        List<VenditaSpedita> tot = new ArrayList<>();
        tot.addAll(cac);
        tot.addAll(caldr);
        return tot;
    }
    /************Consegna Vendita********************/

    public void consegnaVendita(List<VenditaSpedita> list) {
        Iterator<VenditaSpedita> iterator = list.iterator();
        while (iterator.hasNext()) {
            VenditaSpedita vs = iterator.next();
            if (vs.getLuogoDiRitiro().equals(LuogoDiRitiro.NEGOZIO)) {
                aggiornaStatoVendita(list,StatoConsegna.CONSEGNATO_AL_NEGOZIO);
            } else if(vs.getLuogoDiRitiro().equals(LuogoDiRitiro.DOMICILIO)) {
                aggiornaStatoVendita(list,StatoConsegna.CONSEGNATO_AL_CLIENTE);
            }

        }
    }
    /************Preleva Vendita********************/
    public void prelevaVendita(List<VenditaSpedita> list) {
        aggiornaStatoVendita(list,StatoConsegna.RITIRATO);
    }


}
