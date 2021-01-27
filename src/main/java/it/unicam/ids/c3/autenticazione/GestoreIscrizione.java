package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.persistenza.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestoreIscrizione {

    private final ClienteRepository clienteRepository;

    @Autowired
    public GestoreIscrizione(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void iscrizione(String nome,String cognome,String codiceFiscale,String email,String password){
        Cliente cliente = new Cliente(nome,cognome,codiceFiscale,email,password);
        clienteRepository.save(cliente);
    }
}
