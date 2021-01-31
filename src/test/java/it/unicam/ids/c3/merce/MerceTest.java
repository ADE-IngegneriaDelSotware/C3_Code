package it.unicam.ids.c3.merce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MerceTest {

    @Test
    void getID() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        merce.setID(23);
        assertTrue(merce.getID()==23);
    }

    @Test
    void setID() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        merce.setID(23);
        assertTrue(merce.getID()==23);
    }

    @Test
    void getNome() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        assertEquals(merce.getNome(),"fanta");
    }

    @Test
    void setNome() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        merce.setNome("cola");
        assertEquals(merce.getNome(),"cola");
    }

    @Test
    void getCategoria() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        assertEquals(merce.getCategoria(),Categoria.ALIMENTI);
    }

    @Test
    void setCategoria() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        merce.setCategoria(Categoria.GIOCHI);
        assertEquals(merce.getCategoria(),Categoria.GIOCHI);
    }

    @Test
    void getDescrizione() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        assertEquals(merce.getDescrizione(),"lemon");
    }

    @Test
    void setDescrizione() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        merce.setDescrizione("orange");
        assertEquals(merce.getDescrizione(),"orange");
    }
}