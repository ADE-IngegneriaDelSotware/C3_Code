package it.unicam.ids.c3.vendita;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class MerceVenditaTest {

    @Test
    void setMerceAlPubblico(){
        Merce merce = new Merce("fanta",Categoria.ALIMENTI, "fanta lemon");
        MerceAlPubblico merceAlPubblico = new MerceAlPubblico(21, merce);
        MerceVendita mv = new MerceVendita(21, 2, merceAlPubblico);
        assertEquals(mv.getMerceAlPubblico().getMerce(), merce);
    }

}
