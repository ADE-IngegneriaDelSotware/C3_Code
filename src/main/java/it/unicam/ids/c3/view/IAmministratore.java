package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreAmministratore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IAmministratore {

    @Autowired
    private GestoreAmministratore gestoreAmministratore;

    public void setGestoreAmministraotre(GestoreAmministratore gestoreAmministratore) {
        this.gestoreAmministratore = gestoreAmministratore;
    }
}
