package it.unicam.ids.c3.merce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerceInventarioNegozioTest {

    @Test
    void getMerceAlPubblico() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        MerceInventarioNegozio merceInventarioNegozio=new MerceInventarioNegozio(122.3,merceAlPubblico);
        assertEquals(merceAlPubblico,merceInventarioNegozio.getMerceAlPubblico());
    }

    @Test
    void setMerceAlPubblico() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        MerceInventarioNegozio merceInventarioNegozio=new MerceInventarioNegozio(122.3,merceAlPubblico);
        Merce merce1=new Merce("cola",Categoria.ALIMENTI,"zero");
        MerceAlPubblico merceAlPubblico1=new MerceAlPubblico(1.50,merce1);
        merceInventarioNegozio.setMerceAlPubblico(merceAlPubblico1);
        assertEquals(merceAlPubblico1,merceInventarioNegozio.getMerceAlPubblico());
    }
}
