package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.personale.RuoloSistema;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class GestoreCorrieriTest {
    @Autowired
    GestoreCorrieri gc;

    @Test
    void setCorriere() {
        Corriere c = new Corriere(RuoloSistema.CORRIERE, "nomeditta", "indirizzo", "p_iva");
        gc.setCorriere(c);
        assertEquals(gc.getCorriere(), c);
    }

    @Test
    void getVenditeDaRitirare() {
        Merce m = new Merce("merce", Categoria.ABBIGLIAMENTO, "descrizione");
        Merce m1 = new Merce("merce1", Categoria.ALIMENTI, "descrizione1");

        MerceAlPubblico map = new MerceAlPubblico(2, m);
        MerceAlPubblico map1 = new MerceAlPubblico(4, m1);

        MerceVendita mv = new MerceVendita(8, 4, map);
        MerceVendita mv1 = new MerceVendita(8, 2, map1);

        List<MerceVendita> mvl = new ArrayList<>();
        mvl.addAll(List.of(mv, mv1));

        VenditaSpedita vs = new VenditaSpedita(2, mvl, "indirizzo");

        vs.setStatoConsegna(StatoConsegna.IN_ATTESA_DI_RITIRO);

        Corriere c = new Corriere(RuoloSistema.CORRIERE, "nomeditta", "indirizzo", "piva");
        c.addMerceDaSpedire(vs);
        gc.setCorriere(c);

        assertEquals(gc.getVenditeDaRitirare().size(), 1);
    }

    @Test
    void getVenditeRitirate() {
        Merce m = new Merce("merce", Categoria.ABBIGLIAMENTO, "descrizione");
        Merce m1 = new Merce("merce1", Categoria.ALIMENTI, "descrizione1");

        MerceAlPubblico map = new MerceAlPubblico(2, m);
        MerceAlPubblico map1 = new MerceAlPubblico(4, m1);

        MerceVendita mv = new MerceVendita(8, 4, map);
        MerceVendita mv1 = new MerceVendita(8, 2, map1);

        List<MerceVendita> mvl = new ArrayList<>();
        mvl.addAll(List.of(mv, mv1));


        VenditaSpedita vs = new VenditaSpedita(2, mvl, "indirizzo");

        vs.setStatoConsegna(StatoConsegna.RITIRATO);
        Corriere c = new Corriere(RuoloSistema.CORRIERE, "nomeditta", "indirizzo", "piva");
        c.addMerceDaSpedire(vs);

        gc.setCorriere(c);

        assertEquals(gc.getVenditeRitirate().size(), 1);
    }

    @Test
    void getVenditeConsegnate() {
        Merce m = new Merce("merce", Categoria.ABBIGLIAMENTO, "descrizione");
        Merce m1 = new Merce("merce1", Categoria.ALIMENTI, "descrizione1");

        MerceAlPubblico map = new MerceAlPubblico(2, m);
        MerceAlPubblico map1 = new MerceAlPubblico(4, m1);

        MerceVendita mv = new MerceVendita(8, 4, map);
        MerceVendita mv1 = new MerceVendita(8, 2, map1);

        List<MerceVendita> mvl = new ArrayList<>();
        mvl.addAll(List.of(mv, mv1));


        VenditaSpedita vs = new VenditaSpedita(2, mvl, "indirizzo");

        vs.setStatoConsegna(StatoConsegna.CONSEGNATO_AL_CLIENTE);
    }
}
