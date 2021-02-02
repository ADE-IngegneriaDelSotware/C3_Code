package it.unicam.ids.c3.negozio;

import it.unicam.ids.c3.carta.Carta;
import it.unicam.ids.c3.carta.TipoScontoCliente;
import it.unicam.ids.c3.personale.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartaTest {

    Carta carta;

    @BeforeEach
    void create(){
        Cliente cliente = new Cliente("Tizio", "Caio", "tizio@gmail.com", "pwerwet");
        carta = new Carta(cliente, TipoScontoCliente.STUDENTE);
    }

    @Test
    void setCodice() {
        carta.setCodice(321414132);
        assertEquals(carta.getCodice(), 321414132);
    }

}