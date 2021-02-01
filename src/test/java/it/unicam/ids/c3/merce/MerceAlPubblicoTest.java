package it.unicam.ids.c3.merce;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MerceAlPubblicoTest {

    @Test
    void testSetPromozione() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        merceAlPubblico.setPromozione(LocalDate.now().minusDays(1),LocalDate.now().plusDays(10),5,23.5);
        assertTrue(merceAlPubblico.getPromozione().isDisponibile());
        assertTrue(merceAlPubblico.getPromozione().getPrezzoPromozione()==23.5);
    }

    @Test
    void setPrezzo() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        merceAlPubblico.setPrezzo(50.1);
        assertTrue(merceAlPubblico.getPrezzo()==50.1);
    }

    @Test
    void setMerce() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        Merce merce1=new Merce("cola",Categoria.ALIMENTI,"zero");
        merceAlPubblico.setMerce(merce1);
        assertEquals(merceAlPubblico.getMerce(),merce1);
    }

    @Test
    void setSconto() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        merceAlPubblico.setSconto(12.4);
        assertTrue(merceAlPubblico.getSconto()==12.4);
    }
}