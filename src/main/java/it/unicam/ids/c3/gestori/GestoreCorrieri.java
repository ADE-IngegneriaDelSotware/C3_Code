package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.personale.Corriere;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestoreCorrieri {

    private Corriere corriere;

    public void setCorriere(Corriere corriere){
        this.corriere = corriere;
    }

}
