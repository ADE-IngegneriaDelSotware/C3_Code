package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.gestori.GestoreAddetto;
import it.unicam.ids.c3.gestori.GestoreClienti;
import it.unicam.ids.c3.gestori.GestoreCommerciante;
import it.unicam.ids.c3.gestori.GestoreCorrieri;
import it.unicam.ids.c3.personale.AddettoNegozio;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.dbmanager.ClienteRepository;
import it.unicam.ids.c3.personale.Commerciante;
import it.unicam.ids.c3.personale.Corriere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class GestoreAccesso {

    private final ClienteRepository clienteRepository;
    private final GestoreCorrieri gestoreCorrieri;
    private final GestoreClienti gestoreClienti;
    private final GestoreAddetto gestoreAddetto;
    private final GestoreCommerciante gestoreCommerciante;

    @Autowired
    public GestoreAccesso(ClienteRepository clienteRepository, GestoreCorrieri gestoreCorrieri, GestoreClienti gestoreClienti, GestoreAddetto gestoreAddetto, GestoreCommerciante gestoreCommerciante) {
        this.clienteRepository = clienteRepository;
        this.gestoreCorrieri = gestoreCorrieri;
        this.gestoreClienti = gestoreClienti;
        this.gestoreAddetto = gestoreAddetto;
        this.gestoreCommerciante = gestoreCommerciante;
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
                            return "AMMINISTRATORE";
                        case ADDETTONEGOZIO:
                            gestoreAddetto.setAddettoNegozio((AddettoNegozio) cliente.get().getRuolo());
                            return "ADDETTONEGOZIO";
                        case COMMERCIANTE:
                            gestoreCommerciante.setCommerciante((Commerciante) cliente.get().getRuolo());
                            return "COMMERCIANTE";
                    }
                }
            }
        }
        gestoreClienti.setCliente(cliente.get());
        return "CLIENTE";
    }

}
