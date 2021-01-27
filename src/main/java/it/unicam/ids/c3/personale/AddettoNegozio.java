package it.unicam.ids.c3.personale;

import javax.persistence.Entity;

@Entity
public class AddettoNegozio extends Ruolo {

    public AddettoNegozio(RuoloSistema ruolo) {
        super(ruolo);
    }

    public AddettoNegozio(){}
}
