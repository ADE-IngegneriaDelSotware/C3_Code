package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.personale.Amministratore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestoreAmministratore {

    private Amministratore amministratore;

    public void setAmministratore(Amministratore amministratore){
        this.amministratore = amministratore;
    }
}
