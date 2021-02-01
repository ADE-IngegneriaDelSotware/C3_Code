package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.negozio.Carta;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.*;
import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreCheckout {

    private List<MerceVendita> merciCarrello;
    private double prezzoCarrello;
    private MerceRepository merceRepository;
    private MerceAlPubblicoRepository merceAlPubblicoRepository;
    private MerceInventarioNegozioRepository merceInventarioNegozioRepository;
    private MerceVenditaRepository merceVenditaRepository;
    private VenditaRepository venditaRepository;
    private VenditaSpeditaRepository venditaSpeditaRepository;
    private NegozioRepository negozioRepository;
    private ClienteRepository clienteRepository;
    private RuoloRepository ruoloRepository;
    private GestoreCarte gestoreCarte;
    private GestoreMerci gestoreMerci;

    public GestoreCheckout(MerceRepository merceRepository, MerceAlPubblicoRepository merceAlPubblicoRepository, MerceInventarioNegozioRepository merceInventarioNegozioRepository, MerceVenditaRepository merceVenditaRepository, VenditaRepository venditaRepository, VenditaSpeditaRepository venditaSpeditaRepository, NegozioRepository negozioRepository, ClienteRepository clienteRepository, RuoloRepository ruoloRepository, GestoreCarte gestoreCarte, GestoreMerci gestoreMerci) {
        this.merceRepository = merceRepository;
        this.merceAlPubblicoRepository = merceAlPubblicoRepository;
        this.merceInventarioNegozioRepository = merceInventarioNegozioRepository;
        this.merceVenditaRepository = merceVenditaRepository;
        this.venditaRepository = venditaRepository;
        this.venditaSpeditaRepository = venditaSpeditaRepository;
        this.negozioRepository = negozioRepository;
        this.clienteRepository = clienteRepository;
        this.ruoloRepository = ruoloRepository;
        this.gestoreCarte = gestoreCarte;
        this.gestoreMerci = gestoreMerci;
        merciCarrello = new ArrayList<>();
        prezzoCarrello = 0;
    }

    public List<MerceVendita> getMerciCarrello() {
        return this.merciCarrello;
    }

    public void addMerceCarrello(MerceVendita mv) {
        this.merciCarrello.add(mv);
    }

    public double getPrezzoCarrello(){
        return this.prezzoCarrello;
    }

    public void startCarrello(){
        this.merciCarrello = new ArrayList<>();
        this.prezzoCarrello = 0;
    }

    public void svuotaCarrello() {
        merciCarrello.clear();
        prezzoCarrello = 0;
    }

    public double getPrezzo(long id, double quantita, Negozio negozio){
        return Math.round((searchPrezzo(id, negozio, quantita)*quantita)*100.000)/100.000;
    }

    public double searchPrezzo(long id, Negozio negozio, double quantita) {
        return gestoreMerci.searchPrezzo(id, negozio, quantita);
    }

    public double getSconto(long id, Negozio negozio) {
        return gestoreMerci.getSconto(id,negozio);
    }

    public double calcolaPrezzoMerce(double prezzo, double sconto){
        double price = prezzo-((sconto/100)*prezzo);
        return price;
    }

    public double calcolaPrezzoTotale(double prezzo,double sconto) {
        return prezzoCarrello + calcolaPrezzoMerce(prezzo,sconto);
    }
    public double aggiuntaMerceNelCarrello(double prezzo, double sconto, long id, double quantita, Negozio negozio) {
        MerceAlPubblico mp = gestoreMerci.getMerce(id,prezzo,quantita, negozio);
        gestoreMerci.scaloQuantita(mp,quantita, negozio);
        this.prezzoCarrello = calcolaPrezzoTotale(prezzo,sconto);
        MerceVendita mv = new MerceVendita(prezzo - ((sconto/100)*prezzo),quantita,mp);
        merceVenditaRepository.save(mv);
        addMerceCarrello(mv);
        return this.prezzoCarrello;
    }

    public double calcoraResto(double denaro) {
        double resto = denaro - getPrezzoCarrello();
        return resto;
    }

    public void checkoutCompletato() {
        svuotaCarrello();
    }

    public void reinserimentoQuantita(Negozio negozio){
        gestoreMerci.reinserimentoQuantita(negozio, getMerciCarrello());
    }

    public void annullaCheckout(Negozio negozio) {
        reinserimentoQuantita(negozio);
        Vendita v = venditaRepository.findTopByOrderByIdDesc();
        venditaRepository.delete(v);
        svuotaCarrello();
    }

    public boolean verificaCodiceCarta(long cc, Negozio negozio){
        return gestoreCarte.verificaCodiceCarta(cc, negozio);
    }

    public long searchCodiceCartaFromEmail(String email, Negozio negozio){
        return gestoreCarte.searchCodiceCartaFromEmail(email, negozio);
    }

    public List<Corriere> getCorrieriDisponibili(Negozio negozio) {
        List<Corriere> corrieriDisponibiliList = new ArrayList<>();
        if(!negozio.getCorrieri().isEmpty()){
            Iterator<Corriere> corriereIterator = negozio.getCorrieri().iterator();
            while(corriereIterator.hasNext()){
                Corriere corriereDisponibile = corriereIterator.next();
                if(corriereDisponibile.isDisponibilitaRitiro()){
                    corrieriDisponibiliList.add(corriereDisponibile);
                }
            }
        }
        return corrieriDisponibiliList;
    }

    public List<Negozio> getNegoziDisponibili(Negozio negozio) {
        List<Negozio> puntiDiRitiriDisponibiliList = new ArrayList<>();
        Iterator<Negozio> negozioIterator = negozioRepository.findAll().iterator();
        while (negozioIterator.hasNext()){
            Negozio negozioIterato = negozioIterator.next();
            if(negozioIterato.getDisponibilitaRitiro() && negozio.getId()!=negozioIterato.getId()){
                puntiDiRitiriDisponibiliList.add(negozioIterato);
            }
        }
        return puntiDiRitiriDisponibiliList;
    }

    public void registraAcquistoCliente(long cc, Negozio pdr, String indirizzo, Corriere cr, Negozio negozio) {
        Carta carta = searchCarta(cc, negozio);
        VenditaSpedita vs;
        if(indirizzo.isEmpty()){
            vs = new VenditaSpedita(getPrezzoCarrello(), pdr.getIndirizzo(), getMerciCarrello());
            venditaSpeditaRepository.save(vs);
            pdr.addVenditaInNegozioRitiro(vs);
            negozioRepository.save(pdr);
        } else {
            vs = new VenditaSpedita(getPrezzoCarrello(),getMerciCarrello(),indirizzo);
            venditaSpeditaRepository.save(vs);
        }
        venditaSpeditaRepository.save(vs);
        carta.getCliente().getAcquisti().add(vs);
        clienteRepository.save(carta.getCliente());
        negozio.addVendita(vs);
        cr.addMerceDaSpedire(vs);
        negozioRepository.save(negozio);
        ruoloRepository.save(cr);
    }

    public void registraAcquistoCliente(long cc, Negozio negozio) {
        Carta carta = searchCarta(cc, negozio);
        Vendita v = new Vendita(getPrezzoCarrello(), getMerciCarrello());
        carta.getCliente().getAcquisti().add(v);
        clienteRepository.save(carta.getCliente());
        negozio.addVendita(v);
        venditaRepository.save(v);
        negozioRepository.save(negozio);
    }

    public Carta searchCarta(long cc, Negozio negozio) {
        return gestoreCarte.searchCarta(cc, negozio);
    }

    public double applyScontoCarta(long cc, Negozio negozio) {
        if(cc!=0){
            this.prezzoCarrello = prezzoCarrello-((gestoreCarte.calcolaScontoCarta(cc, negozio)/100) * prezzoCarrello);
        }
        return prezzoCarrello;
    }

    public void addVenditaInventario(Negozio negozio) {
        Vendita v = new Vendita(getPrezzoCarrello(), getMerciCarrello());
        negozio.addVendita(v);
        venditaRepository.save(v);
        negozioRepository.save(negozio);
    }
}
