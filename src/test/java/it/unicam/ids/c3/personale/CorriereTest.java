package it.unicam.ids.c3.personale;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class CorriereTest {

    Corriere corriere = new Corriere(RuoloSistema.CORRIERE, "GLS", "Via Alberto", "4345435");
    List<MerceVendita> lista = new ArrayList<>();

    @BeforeEach
    void create(){
        Merce merce = new Merce("iphone", Categoria.TECNOLOGIA, "256 gb di ram");
        MerceAlPubblico merceAlPubblico = new MerceAlPubblico(32, merce);
        MerceVendita merceVendita = new MerceVendita(32, 1, merceAlPubblico);
        lista.add(merceVendita);
        VenditaSpedita vs = new VenditaSpedita(34, lista, "Via gls");
        corriere.addMerceDaSpedire(vs);
    }

    @Test
    void addMerceDaSpedire() {
        assertEquals(corriere.getVendite().size(), 1);
        VenditaSpedita vs = new VenditaSpedita(56, lista, "Via piazza");
        corriere.addMerceDaSpedire(vs);
        assertEquals(corriere.getVendite().size(), 2);
    }

    @Test
    void getVenditePerStato() {
        VenditaSpedita vs = new VenditaSpedita(79, lista, "Via qwerty");
        assertEquals(vs.getStatoConsegna(), StatoConsegna.IN_ATTESA_DI_RITIRO);
        corriere.addMerceDaSpedire(vs);
        assertEquals(corriere.getVenditePerStato(StatoConsegna.IN_ATTESA_DI_RITIRO).size(), 2);
        vs.setStatoConsegna(StatoConsegna.RITIRATO);
        assertFalse(corriere.getVenditePerStato(StatoConsegna.IN_ATTESA_DI_RITIRO).size() == 2);
        assertEquals(corriere.getVenditePerStato(StatoConsegna.RITIRATO).size(), 1);
    }

}
