package it.unicam.ids.c3.vendita;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VenditaTest {

    List<MerceVendita> list = new ArrayList<>();
    Vendita vendita;
    MerceVendita mv;

    @BeforeEach
    void init(){
        Merce merce = new Merce("fanta", Categoria.ALIMENTI, "fanta lemon");
        MerceAlPubblico merceAlPubblico = new MerceAlPubblico(21, merce);
        mv = new MerceVendita(32, 2, merceAlPubblico);
        list.add(mv);
        vendita = new Vendita(32, list);
    }

    @Test
    void getListaMerceVendita() {
        assertEquals(vendita.getListaMerceVendita().size(), 1);
        Merce merce = new Merce("fanta", Categoria.ALIMENTI, "fanta orange");
        MerceAlPubblico merceAlPubblico = new MerceAlPubblico(21, merce);
        MerceVendita merceVendita  = new MerceVendita(23, 2, merceAlPubblico);
        list.add(merceVendita);
        Vendita vendita = new Vendita(32, list);
        assertTrue(vendita.getListaMerceVendita().size()==2);
    }

}
