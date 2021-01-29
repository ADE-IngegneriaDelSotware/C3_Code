package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.vendita.LuogoDiRitiro;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreCorrieri {

    private Corriere corriere;
    private GestoreVendite gestoreVendite;

    public GestoreCorrieri(GestoreVendite gestoreVendite) {
        this.gestoreVendite = gestoreVendite;
    }

    public void setCorriere(Corriere corriere){
        this.corriere = corriere;
    }

    public Corriere getCorriere() {
        return corriere;
    }

    public void aggiornaStatoVendita(List<VenditaSpedita> list, StatoConsegna sc){
        gestoreVendite.aggiornaStatoVendita(list, sc, getCorriere());
    }

    /************Consulta Inventario********************/
    public List<VenditaSpedita> getVenditeDaRitirare() {
        return gestoreVendite.getVenditeDaRitirare(getCorriere());
    }

    public List<VenditaSpedita> getVenditeRitirate() {
        return gestoreVendite.getVenditeRitirate(getCorriere());
    }

    public List<VenditaSpedita> getVenditeConsegnate() {
        return gestoreVendite.getVenditeConsegnate(getCorriere());
    }

    /************Consegna Vendita********************/

    public void consegnaVendita(List<VenditaSpedita> list) {
        Iterator<VenditaSpedita> iterator = list.iterator();
        while (iterator.hasNext()) {
            VenditaSpedita vs = iterator.next();
            if (vs.getLuogoDiRitiro().equals(LuogoDiRitiro.NEGOZIO)) {
                gestoreVendite.aggiornaStatoVendita(list,StatoConsegna.CONSEGNATO_AL_NEGOZIO, getCorriere());
            } else if(vs.getLuogoDiRitiro().equals(LuogoDiRitiro.DOMICILIO)) {
                aggiornaStatoVendita(list,StatoConsegna.CONSEGNATO_AL_CLIENTE);
            }

        }
    }

    /************Preleva Vendita********************/
    public void prelevaVendita(List<VenditaSpedita> list) {
        gestoreVendite.aggiornaStatoVendita(list,StatoConsegna.RITIRATO, getCorriere());
    }


}
