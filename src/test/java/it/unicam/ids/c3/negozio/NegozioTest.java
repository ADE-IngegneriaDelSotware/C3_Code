package it.unicam.ids.c3.negozio;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.personale.AddettoNegozio;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.personale.RuoloSistema;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NegozioTest {

    Negozio negozio;

    @BeforeEach
    void init(){
        negozio = new Negozio("Pizzeria", "Via Morelle", "54356", List.of(Categoria.ALIMENTI, Categoria.TECNOLOGIA));
    }

    @Test
    void addCarta() {
        Cliente cliente = new Cliente("Tizio", "Caio", "tizio@gmail.com", "pwerwet");
        Carta carta = new Carta(cliente, TipoScontoCliente.LAVORATORE);
        negozio.addCarta(carta);
        assertEquals(negozio.getCarte().get(0), carta);
    }


    @Test
    void addCorriere() {
        Corriere corriere = new Corriere(RuoloSistema.CORRIERE, "GLS", "Via Alberto", "4345435");
        negozio.addCorriere(corriere);
        assertEquals(negozio.getCorrieri().get(0), corriere);
    }

    @Test
    void addMerceInventarioNegozio() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        MerceInventarioNegozio min = new MerceInventarioNegozio(3, merceAlPubblico);
        assertEquals(negozio.getMerceInventarioNegozio().size(), 0);
        negozio.addMerceInventarioNegozio(min);
        assertEquals(negozio.getMerceInventarioNegozio().size(), 1);
        assertEquals(negozio.getMerceInventarioNegozio().get(0), min);
    }

    @Test
    void addVendita() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        MerceVendita mv = new MerceVendita(54, 2, merceAlPubblico);
        List<MerceVendita> lista = new ArrayList<>();
        lista.add(mv);
        Vendita vendita = new Vendita(70, lista);
        assertEquals(negozio.getVendite().size(), 0);
        negozio.addVendita(vendita);
        assertEquals(negozio.getVendite().size(), 1);
        assertEquals(negozio.getVendite().get(0), vendita);
    }

    @Test
    void addVenditaInNegozioRitiro() {
        Merce merce=new Merce("fanta",Categoria.ALIMENTI,"lemon");
        MerceAlPubblico merceAlPubblico=new MerceAlPubblico(23.5,merce);
        MerceVendita mv = new MerceVendita(54, 2, merceAlPubblico);
        List<MerceVendita> lista = new ArrayList<>();
        lista.add(mv);
        VenditaSpedita vs = new VenditaSpedita(21, "via della pieve", lista);
        negozio.addVenditaInNegozioRitiro(vs);
        assertEquals(negozio.getVenditeNegozioRitiro().size(), 1);
        assertEquals(negozio.getVenditeNegozioRitiro().get(0), vs);
    }

    @Test
    void setDisponibilitaRitiro() {
        negozio.setDisponibilitaRitiro(false);
        assertFalse(negozio.getDisponibilitaRitiro());
        negozio.setDisponibilitaRitiro(true);
        assertTrue(negozio.getDisponibilitaRitiro());
    }

    @Test
    void addAddettoNegozio() {
        AddettoNegozio addettoNegozio = new AddettoNegozio(RuoloSistema.ADDETTONEGOZIO);
        Cliente cliente = new Cliente("Tizio", "Caio", "tizio@gmail.com", "pwerwet");
        cliente.setRuolo(addettoNegozio);
        assertTrue(negozio.getAddetti().size()==0);
        negozio.addAddettoNegozio(addettoNegozio);
        assertTrue(negozio.getAddetti().size()==1);
    }
}