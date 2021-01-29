package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.*;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.Vendita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    public GestoreCheckout(MerceRepository merceRepository, MerceAlPubblicoRepository merceAlPubblicoRepository, MerceInventarioNegozioRepository merceInventarioNegozioRepository, MerceVenditaRepository merceVenditaRepository, VenditaRepository venditaRepository) {
        this.merceRepository = merceRepository;
        this.merceAlPubblicoRepository = merceAlPubblicoRepository;
        this.merceInventarioNegozioRepository = merceInventarioNegozioRepository;
        this.merceVenditaRepository = merceVenditaRepository;
        this.venditaRepository = venditaRepository;
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
        return searchPrezzo(id, negozio)*quantita;
    }

    public double searchPrezzo(long id, Negozio negozio) {
        Iterator<MerceInventarioNegozio> min = negozio.getMerceInventarioNegozio().iterator();
        while(min.hasNext()){
            MerceInventarioNegozio m = min.next();
            MerceAlPubblico map = m.getMerceAlPubblico();
            Merce merce = map.getMerce();
            if(merce.getID() == id){
                return map.getPrezzo();
            }
        }
        return 0;
    }

    public double getSconto(long id, Negozio negozio) {
        Iterator<MerceInventarioNegozio> it = negozio.getMerceInventarioNegozio().iterator();
        while (it.hasNext()){
            MerceInventarioNegozio min = it.next();
            if(min.getMerceAlPubblico().getMerce().getID()==id){
                return min.getMerceAlPubblico().getSconto();
            }
        }
        return 0;
    }

    public double calcolaPrezzoMerce(double prezzo, double sconto){
        double price = prezzo-((sconto/100)*prezzo);
        return price;
    }

    public double calcolaPrezzoTotale(double prezzo,double sconto) {
        return prezzoCarrello + calcolaPrezzoMerce(prezzo,sconto);
    }

    public void scaloQuantita(MerceAlPubblico mp , double quantita, Negozio negozio){
        for(MerceInventarioNegozio min : negozio.getMerceInventarioNegozio()){
            if(min.getMerceAlPubblico().equals(mp)){
                min.setQuantita(min.getQuantita()-quantita);
                merceInventarioNegozioRepository.save(min);
            }
        }
    }

    public double aggiuntaMerceNelCarrello(double prezzo, double sconto, long id, double quantita, Negozio negozio) {
        MerceAlPubblico mp = getMerce(id,prezzo,quantita, negozio);
        scaloQuantita(mp,quantita, negozio);
        this.prezzoCarrello = calcolaPrezzoTotale(prezzo,sconto);
        MerceVendita mv = new MerceVendita(prezzo - ((sconto/100)*prezzo),quantita,mp);
        merceVenditaRepository.save(mv);
        addMerceCarrello(mv);
        return this.prezzoCarrello;
    }

    public MerceAlPubblico getMerce(long id, double prezzo, double quantita, Negozio negozio) {
        Iterator<MerceInventarioNegozio> it = negozio.getMerceInventarioNegozio().iterator();
        while (it.hasNext()){
            MerceInventarioNegozio min = it.next();
            if(min.getMerceAlPubblico().getMerce().getID()==id){
                return min.getMerceAlPubblico();
            }
        }
        MerceAlPubblico ma;
        Optional<Merce> merceOptional = merceRepository.findById(id);
        if(merceOptional.isPresent()){
            ma = new MerceAlPubblico((prezzo/quantita),merceOptional.get());
        } else {
            Merce merce1 = new Merce();
            merceRepository.save(merce1);
            ma = new MerceAlPubblico((prezzo/quantita),merce1);
        }
        merceAlPubblicoRepository.save(ma);
        return ma;
    }

    public double calcoraResto(double denaro) {
        double resto = denaro - getPrezzoCarrello();
        return resto;
    }

    public void checkoutCompletato() {
        svuotaCarrello();
    }

    public void reinserimentoQuantita(Negozio negozio){
        for(MerceVendita mv : getMerciCarrello()){
            for(MerceInventarioNegozio min : negozio.getMerceInventarioNegozio()){
                if(mv.getMerceAlPubblico().equals(min.getMerceAlPubblico())){
                    min.setQuantita(min.getQuantita()+mv.getQuantitaVenduta());
                }
            }
        }
    }

    public void annullaCheckout(Negozio negozio) {
        reinserimentoQuantita(negozio);
        Vendita v = venditaRepository.findTopByOrderByIdDesc();
        venditaRepository.delete(v);
        svuotaCarrello();
    }




}
