package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.personale.AddettoNegozio;
import it.unicam.ids.c3.vendita.MerceVendita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreAddetto {

    private AddettoNegozio addettoNegozio;
    private Negozio negozio;
    private NegozioRepository negozioRepository;
    private List<MerceVendita> merciCarrello;
    private double prezzoCarrello;

    public GestoreAddetto(NegozioRepository negozioRepository) {
        this.negozioRepository = negozioRepository;
        this.merciCarrello = new ArrayList<>();
        this.prezzoCarrello = 0;
        //this.negozio = setNegozio();
    }

    public Negozio getNegozio(){
        return this.negozio;
    }

    /**********Checkout Merce*********/

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

    public double getPrezzo(long id, double quantita) {
        System.out.println(getNegozio()); // questo funziona quindi il negozio lo vede
        System.out.println(getNegozio().getMerceInventarioNegozio().size()); //qui scoppia non riesce ad andare sulle merci



        return searchPrezzo(id)*quantita;
    }

    public double searchPrezzo(long id) {
        if(!negozio.getMerceInventarioNegozio().isEmpty()){
            Iterator<MerceInventarioNegozio> it = getNegozio().getMerceInventarioNegozio().iterator();
            while (it.hasNext()){
                MerceInventarioNegozio min = it.next();
                if(min.getMerceAlPubblico().getMerce().getID()==id){
                    return min.getMerceAlPubblico().getPrezzo();
                }
            }
        }
        return 0;
    }

    public double getSconto(int id) {
        Iterator<MerceInventarioNegozio> it = getNegozio().getMerceInventarioNegozio().iterator();
        while (it.hasNext()){
            MerceInventarioNegozio min = it.next();
            if(min.getMerceAlPubblico().getMerce().getID()==id){
                return min.getMerceAlPubblico().getSconto();
            }
        }
        return 0;
    }

    public void setAddettoNegozio(AddettoNegozio addettoNegozio){
        this.addettoNegozio = addettoNegozio;
    }

    public void setNegozio(Negozio negozio){
        this.negozio = negozio;
    }

}
