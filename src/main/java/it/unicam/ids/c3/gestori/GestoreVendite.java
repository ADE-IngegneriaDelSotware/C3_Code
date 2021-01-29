package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreVendite {

    public void aggiornaStatoVendita(List<VenditaSpedita> list, StatoConsegna sc, Corriere corriere) {
        List<VenditaSpedita> lista = list;
        for(VenditaSpedita vs : lista){
            Iterator<VenditaSpedita> iterator = corriere.getVendite().iterator();
            while (iterator.hasNext()){
                VenditaSpedita vsIterata = iterator.next();
                if(vsIterata.equals(vs)){
                    vs.setStatoConsegna(sc);
                }
            }
        }
    }

    public List<VenditaSpedita> getVenditeDaRitirare(Corriere corriere){
        return corriere.getVenditePerStato(StatoConsegna.IN_ATTESA_DI_RITIRO);
    }

    public List<VenditaSpedita> getVenditeRitirate(Corriere corriere){
        return corriere.getVenditePerStato(StatoConsegna.RITIRATO);
    }

    public List<VenditaSpedita> getVenditeConsegnate(Corriere corriere){
        List<VenditaSpedita> cac = corriere.getVenditePerStato(StatoConsegna.CONSEGNATO_AL_CLIENTE);
        List<VenditaSpedita> caldr = corriere.getVenditePerStato(StatoConsegna.CONSEGNATO_AL_NEGOZIO);
        List<VenditaSpedita> tot = new ArrayList<>();
        tot.addAll(cac);
        tot.addAll(caldr);
        return tot;
    }

}
