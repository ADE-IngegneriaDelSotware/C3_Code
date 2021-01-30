package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.gestori.*;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.persistenza.RuoloRepository;
import it.unicam.ids.c3.personale.*;
import it.unicam.ids.c3.persistenza.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.Optional;

@Service
@Transactional
public class GestoreAccesso {

    private final ClienteRepository clienteRepository;
    private final RuoloRepository ruoloRepository;
    private final NegozioRepository negozioRepository;
    private final GestoreCorrieri gestoreCorrieri;
    private final GestoreClienti gestoreClienti;
    private final GestoreAddetto gestoreAddetto;
    private final GestoreCommerciante gestoreCommerciante;
    private final GestoreAmministratore gestoreAmministratore;

    @Autowired
    public GestoreAccesso(ClienteRepository clienteRepository, RuoloRepository ruoloRepository, NegozioRepository negozioRepository, GestoreCorrieri gestoreCorrieri, GestoreClienti gestoreClienti, GestoreAddetto gestoreAddetto, GestoreCommerciante gestoreCommerciante, GestoreAmministratore gestoreAmministratore) {
        this.clienteRepository = clienteRepository;
        this.ruoloRepository = ruoloRepository;
        this.negozioRepository = negozioRepository;
        this.gestoreCorrieri = gestoreCorrieri;
        this.gestoreClienti = gestoreClienti;
        this.gestoreAddetto = gestoreAddetto;
        this.gestoreCommerciante = gestoreCommerciante;
        this.gestoreAmministratore = gestoreAmministratore;
    }


    public String accesso(String email, String password) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent()) {
            if(cliente.get().getPassword().equals(password)){
                if(cliente.get().getRuolo()!=null){
                    switch (cliente.get().getRuolo().getRuoloSistema()){
                        case CORRIERE:
                            gestoreCorrieri.setCorriere((Corriere) cliente.get().getRuolo());
                            return "CORRIERE";
                        case AMMINISTRATORE:
                            gestoreAmministratore.setAmministratore((Amministratore) cliente.get().getRuolo());
                            return "AMMINISTRATORE";
                        case ADDETTONEGOZIO:
                            setNegozioAddetto(cliente.get());
                            return "ADDETTONEGOZIO";

                        case COMMERCIANTE:
                            setNegozioCommerciante(cliente.get());
                            return "COMMERCIANTE";
                    }
                }
                gestoreClienti.setCliente(cliente.get());
                return "CLIENTE";
            }
        }
        throw new IllegalStateException("CREDENZIALI ACCESSO ERRATE");
    }

    private void setNegozioAddetto(Cliente cliente){
        Iterator<Negozio> negozioIterator = negozioRepository.findAll().iterator();
        while (negozioIterator.hasNext()){
            Negozio negozio = negozioIterator.next();
            Iterator<AddettoNegozio> addettoNegozioIterator = negozio.getAddetti().iterator();
            while (addettoNegozioIterator.hasNext()){
                AddettoNegozio addettoNegozio = addettoNegozioIterator.next();
                if(addettoNegozio.equals(cliente.getRuolo())){
                    gestoreAddetto.setNegozio(negozio);
                }
            }
        }
    }

    private void setNegozioCommerciante(Cliente cliente){
        Iterator<Negozio> negozioIterator = negozioRepository.findAll().iterator();
        while (negozioIterator.hasNext()){
            Negozio negozio = negozioIterator.next();
            Iterator<AddettoNegozio> addettoNegozioIterator = negozio.getAddetti().iterator();
            while (addettoNegozioIterator.hasNext()){
                AddettoNegozio addettoNegozio = addettoNegozioIterator.next();
                if(addettoNegozio.equals(cliente.getRuolo())){
                    gestoreCommerciante.setNegozio(negozio);
                }
            }
        }
    }

 }
