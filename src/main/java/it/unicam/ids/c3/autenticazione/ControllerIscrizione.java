package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ControllerIscrizione {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ControllerIscrizione(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public void iscrizione(String nome,String cognome,String codiceFiscale,String email,String username,String password){
        Cliente cliente = new Cliente(nome,cognome,codiceFiscale,email,username,password);
        clienteRepository.save(cliente);
    }
}
