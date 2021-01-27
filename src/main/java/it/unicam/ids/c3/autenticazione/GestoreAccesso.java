package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.gestori.*;
import it.unicam.ids.c3.personale.*;
import it.unicam.ids.c3.persistenza.ClienteRepository;
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
    private final GestoreAmministratore gestoreAmministratore;

    @Autowired
    public GestoreAccesso(ClienteRepository clienteRepository, GestoreCorrieri gestoreCorrieri, GestoreClienti gestoreClienti, GestoreAddetto gestoreAddetto, GestoreCommerciante gestoreCommerciante, GestoreAmministratore gestoreAmministratore) {
        this.clienteRepository = clienteRepository;
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
