package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.Negozio;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GestoreCheckoutTest {

    @Autowired
    GestoreCheckout gestoreCheckout;
    Negozio negozio = new Negozio("capannina", "via la mimosa", "456786757789", List.of(Categoria.ABBIGLIAMENTO, Categoria.ALIMENTI));


    @BeforeAll
    void init(){
        Merce merce = new Merce("cola", Categoria.ALIMENTI, "coca cola zero");
        Merce merce1 = new Merce("fanta", Categoria.ALIMENTI, "fanta zero");
        MerceAlPubblico mp = new MerceAlPubblico(1.3, merce);
        MerceAlPubblico mp1 = new MerceAlPubblico(1.4, merce1);
        MerceInventarioNegozio min = new MerceInventarioNegozio(30, mp);
        MerceInventarioNegozio min1 = new MerceInventarioNegozio(40, mp1);
        negozio.addMerceInventarioNegozio(min);
        negozio.addMerceInventarioNegozio(min1);
    }

    @Test
    void getPrezzoTest(){
        double prezzo = gestoreCheckout.getPrezzo(negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico().getMerce().getID(), 3, negozio);
        assertTrue(prezzo==3.9);
    }

}
