package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.carta.Carta;
import it.unicam.ids.c3.Negozio;
import it.unicam.ids.c3.carta.TipoScontoCliente;
import it.unicam.ids.c3.personale.Cliente;
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
class GestoreCarteTest {

    @Autowired
    GestoreCarte gestoreCarte;
    Cliente cliente = new Cliente("Tizio", "Caio", "tizio@gmail.com", "pwerwet");
    Carta carta = new Carta(cliente, TipoScontoCliente.STUDENTE);

    Negozio negozio = new Negozio("capannina", "via la mimosa", "456786757789", List.of(Categoria.ABBIGLIAMENTO, Categoria.ALIMENTI));

    @BeforeAll
    void init(){
        negozio.addCarta(carta);
        carta.setCodice(1234567);
    }


    @Test
    void verificaCodiceCarta(){
        assertTrue(gestoreCarte.verificaCodiceCarta(carta.getCodice(), negozio));
    }

    @Test
    void searchCodiceCartaFromEmailTest(){
        long cc = gestoreCarte.searchCodiceCartaFromEmail("tizio@gmail.com" , negozio);
        assertTrue(cc == 1234567);
    }

    @Test
    void searchCarta(){
        Carta carta = gestoreCarte.searchCarta(1234567, negozio);
        assertEquals(this.carta, carta);
    }

    @Test
    void calcolaScontoCartaTest(){
        double sconto = gestoreCarte.calcolaScontoCarta(1234567, negozio);
        assertTrue(sconto!=0);
    }
}
