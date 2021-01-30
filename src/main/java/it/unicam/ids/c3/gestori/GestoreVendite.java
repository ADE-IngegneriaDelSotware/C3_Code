package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.ClienteRepository;
import it.unicam.ids.c3.persistenza.VenditaSpeditaRepository;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GestoreVendite {

    private ClienteRepository clienteRepository;
    private VenditaSpeditaRepository venditaSpeditaRepository;

    public GestoreVendite(ClienteRepository clienteRepository, VenditaSpeditaRepository venditaSpeditaRepository) {
        this.clienteRepository = clienteRepository;
        this.venditaSpeditaRepository = venditaSpeditaRepository;
    }

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

    public List<VenditaSpedita> getAcquistiClienteDaRitirare(String email, Negozio negozio) {
        List<VenditaSpedita> list = new ArrayList<>();
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if(cliente.isPresent()){
            if(!cliente.get().getAcquisti().isEmpty()){
                Iterator<VenditaSpedita> venditeNegozio = negozio.getVenditeNegozioRitiro().iterator();
                while(venditeNegozio.hasNext()){
                    VenditaSpedita vs = venditeNegozio.next();
                    if(vs.getStatoConsegna().equals(StatoConsegna.CONSEGNATO_AL_NEGOZIO)){
                        Iterator<Vendita> venditaIterator = cliente.get().getAcquisti().iterator();
                        while(venditaIterator.hasNext()){
                            Vendita vendita = venditaIterator.next();
                            if(vendita.getId() == vs.getId()){
                                list.add(vs);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    public void confermaConsegnaVenditaAssegnata(List<VenditaSpedita> vendite) {
        aggiornaStatoVendita(vendite,StatoConsegna.CONSEGNATO_AL_CLIENTE);
        venditaSpeditaRepository.saveAll(vendite);
    }

    public void aggiornaStatoVendita(List<VenditaSpedita> list, StatoConsegna sc) {
        Iterator<VenditaSpedita> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next().setStatoConsegna(sc);
        }
    }
}
