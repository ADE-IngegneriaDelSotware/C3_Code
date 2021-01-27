package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.personale.AddettoNegozio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestoreAddetto {

    private AddettoNegozio addettoNegozio;

    public void setAddettoNegozio(AddettoNegozio addettoNegozio){
        this.addettoNegozio = addettoNegozio;
    }

}
