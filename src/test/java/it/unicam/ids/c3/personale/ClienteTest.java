package it.unicam.ids.c3.personale;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ClienteTest {

    Cliente cliente = new Cliente("Tizio", "Caio", "tizio@gmail.com", "pwerwet");
    List<MerceVendita> lista = new ArrayList<>();
    VenditaSpedita vs;

    @BeforeEach
    void create(){
        Merce merce = new Merce("iphone", Categoria.TECNOLOGIA, "256 gb di ram");
        MerceAlPubblico merceAlPubblico = new MerceAlPubblico(32, merce);
        MerceVendita merceVendita = new MerceVendita(32, 1, merceAlPubblico);
        lista.add(merceVendita);
        vs = new VenditaSpedita(34, lista, "Via gls");
    }

    @Test
    void setRuolo() {
        Amministratore amministratore = new Amministratore(RuoloSistema.AMMINISTRATORE);
        cliente.setRuolo(amministratore);
        assertTrue(cliente.getRuolo().equals(amministratore));
    }


    @Test
    void addAcquisto() {
        cliente.addAcquisto(vs);
        assertEquals(cliente.getAcquisti().get(0).getListaMerceVendita(), lista);
    }
}
