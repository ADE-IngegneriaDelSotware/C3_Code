package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.personale.Commerciante;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestoreCommerciante {

    private Commerciante commerciante;


    public void setCommerciante(Commerciante commerciante){
        this.commerciante = commerciante;
    }
}
