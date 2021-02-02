package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.Negozio;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GestoreMerciTest {
    private Negozio negozio=new Negozio("capannina","via la mimosa","456786757789", List.of(Categoria.ABBIGLIAMENTO,Categoria.ALIMENTI));
    private long id;
    private long id1;
    @Autowired
    GestoreMerci gestoreMerci;
    @BeforeAll
    void init() {
        gestoreMerci.addMerce("fanta","lemon",Categoria.ALIMENTI,12.9,1.5,10,negozio);
        gestoreMerci.addMerce("cola","zero",Categoria.ALIMENTI,10.9,2.5,20,negozio);
        id=negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico().getMerce().getID();
        id1=negozio.getMerceInventarioNegozio().get(1).getMerceAlPubblico().getMerce().getID();
    }
    @Test
    void addMerce() {
        gestoreMerci.addMerce("sprite","lite",Categoria.ALIMENTI,8,5,10,negozio);
        assertTrue(negozio.getMerceInventarioNegozio().size()==3);
    }

    @Test
    void searchPrezzo() {
        assertTrue(gestoreMerci.searchPrezzo(id,negozio,2)==1.5);
        assertTrue(gestoreMerci.searchPrezzo(id,negozio,150)==0);
    }

    @Test
    void getSconto() {
        assertTrue(gestoreMerci.getSconto(id,negozio)==10);
        assertTrue(gestoreMerci.getSconto(id1,negozio)==20);
    }

    @Test
    void scaloQuantita() {
        assertTrue(negozio.getMerceInventarioNegozio().get(0).getQuantita()==12.9);
        gestoreMerci.scaloQuantita(negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico(),1,negozio);
        assertTrue(negozio.getMerceInventarioNegozio().get(0).getQuantita()==11.9);
    }

    @Test
    void removeMerce() {
        List<MerceInventarioNegozio> lmin =List.of(negozio.getMerceInventarioNegozio().get(1));
        gestoreMerci.removeMerce(lmin,1.9,negozio);
        assertTrue(negozio.getMerceInventarioNegozio().get(1).getQuantita()==9);
    }

    @Test
    void getMerce() {
        assertEquals(gestoreMerci.getMerce(id,12.3,3,negozio),negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico());
    }

    @Test
    void getPromozioniAttive() {
        negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico().setPromozione(LocalDate.now().minusDays(3),LocalDate.now().plusDays(10),10,1.5);
        assertTrue(!gestoreMerci.getPromozioniAttive(negozio).isEmpty());
        negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico().getPromozione().setDisponibile(false);
        assertTrue(gestoreMerci.getPromozioniAttive(negozio).isEmpty());
    }

    @Test
    void addPromozione() {
        assertTrue(!negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico().getPromozione().isDisponibile());
        gestoreMerci.addPromozione(negozio.getMerceInventarioNegozio().get(0), LocalDate.now().minusDays(1),LocalDate.now().plusDays(20),10);
        assertTrue(negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico().getPromozione().isDisponibile());
    }

    @Test
    void rimuoviPromozione() {
        gestoreMerci.rimuoviPromozione(List.of(negozio.getMerceInventarioNegozio().get(0)));
        assertTrue(!negozio.getMerceInventarioNegozio().get(0).getMerceAlPubblico().getPromozione().isDisponibile());
    }
}