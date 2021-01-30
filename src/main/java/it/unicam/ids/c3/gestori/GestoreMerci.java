package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.MerceAlPubblicoRepository;
import it.unicam.ids.c3.persistenza.MerceInventarioNegozioRepository;
import it.unicam.ids.c3.persistenza.MerceRepository;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.vendita.MerceVendita;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreMerci {

    private MerceInventarioNegozioRepository merceInventarioNegozioRepository;
    private MerceRepository merceRepository;
    private MerceAlPubblicoRepository merceAlPubblicoRepository;
    private NegozioRepository negozioRepository;

    public GestoreMerci(MerceInventarioNegozioRepository merceInventarioNegozioRepository, MerceRepository merceRepository, MerceAlPubblicoRepository merceAlPubblicoRepository, NegozioRepository negozioRepository) {
        this.merceInventarioNegozioRepository = merceInventarioNegozioRepository;
        this.merceRepository = merceRepository;
        this.merceAlPubblicoRepository = merceAlPubblicoRepository;
        this.negozioRepository = negozioRepository;
    }

    public double searchPrezzo(long id, Negozio negozio, double quantita) {
        Iterator<MerceInventarioNegozio> min = negozio.getMerceInventarioNegozio().iterator();
        while(min.hasNext()){
            MerceInventarioNegozio m = min.next();
            if(m.getQuantita() >= quantita){
                MerceAlPubblico map = m.getMerceAlPubblico();
                Merce merce = map.getMerce();
                if(merce.getID() == id){
                    return map.getPrezzo();
                }
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

    public void scaloQuantita(MerceAlPubblico mp , double quantita, Negozio negozio){
        for(MerceInventarioNegozio min : negozio.getMerceInventarioNegozio()){
            if(min.getMerceAlPubblico().equals(mp)){
                min.setQuantita(min.getQuantita()-quantita);
                merceInventarioNegozioRepository.save(min);
            }
        }
    }

    public void reinserimentoQuantita(Negozio negozio, List<MerceVendita> merciCarrello){
        for(MerceVendita mv : merciCarrello){
            for(MerceInventarioNegozio min : negozio.getMerceInventarioNegozio()){
                if(mv.getMerceAlPubblico().equals(min.getMerceAlPubblico())){
                    min.setQuantita(min.getQuantita()+mv.getQuantitaVenduta());
                    merceInventarioNegozioRepository.save(min);
                }
            }
        }
    }

    public String getInfoMerce(MerceInventarioNegozio min) {
        String promozione;
        if(min.getMerceAlPubblico().getPromozione().isDisponibile()){
            promozione = ", si trova in promozione";
        } else {
            promozione = ", non si trova in promozione";
        }
        return "Nome: " + min.getMerceAlPubblico().getMerce().getNome() + ", ID: " +
                min.getMerceAlPubblico().getMerce().getID() + ", categoria: " + min.getMerceAlPubblico().getMerce().getCategoria() +
                ", in quantita: " + min.getQuantita() + ", con uno sconto: " + min.getMerceAlPubblico().getSconto()
                + promozione;
    }

    public void addMerce(String nome, String descrizione, Categoria categoria, double quantita, double prezzo, double sconto, Negozio negozio) {
        Merce merce = new Merce(nome,categoria,descrizione);
        merceRepository.save(merce);
        MerceAlPubblico mp = new MerceAlPubblico(prezzo,merce,sconto);
        merceAlPubblicoRepository.save(mp);
        MerceInventarioNegozio min = new MerceInventarioNegozio(quantita,mp);
        merceInventarioNegozioRepository.save(min);
        negozio.addMerceInventarioNegozio(min);
        negozioRepository.save(negozio);
    }

    public void removeMerce(List<MerceInventarioNegozio> list, double quantita, Negozio negozio){
        Iterator<MerceInventarioNegozio> minIterator = negozio.getMerceInventarioNegozio().iterator();
        while (minIterator.hasNext()) {
            MerceInventarioNegozio min=minIterator.next();
            Iterator<MerceInventarioNegozio> daRimuovere = list.iterator();
            while(daRimuovere.hasNext()) {
                MerceInventarioNegozio minDaRimuovere= daRimuovere.next();
                if(min.equals(minDaRimuovere)) {
                    double nuovaQuantita=min.getQuantita()-quantita;
                    min.setQuantita(nuovaQuantita);
                    merceInventarioNegozioRepository.save(min);
                }
            }
        }
        negozioRepository.save(negozio);
    }

    public List<MerceInventarioNegozio> getPromozioniAttive(Negozio negozio) {
        List<MerceInventarioNegozio> list = new ArrayList<>();
        if(!negozio.getMerceInventarioNegozio().isEmpty()){
            Iterator<MerceInventarioNegozio> iterator = negozio.getMerceInventarioNegozio().iterator();
            while (iterator.hasNext()){
                MerceInventarioNegozio min = iterator.next();
                MerceAlPubblico map = min.getMerceAlPubblico();
                if(map.getPromozione().isDisponibile()){
                    list.add(min);
                }
            }
        }
        return list;
    }

    public List<MerceInventarioNegozio> getPromozioniPossibili(Negozio negozio) {
        List<MerceInventarioNegozio> list = new ArrayList<>();
        if(!negozio.getMerceInventarioNegozio().isEmpty()){
            Iterator<MerceInventarioNegozio> iterator = negozio.getMerceInventarioNegozio().iterator();
            while (iterator.hasNext()){
                MerceInventarioNegozio min = iterator.next();
                if(!min.getMerceAlPubblico().getPromozione().isDisponibile()){
                    list.add(min);
                }
            }
        }
        return list;
    }

    public void addPromozione(MerceInventarioNegozio miv, LocalDate di, LocalDate df, double pp) {
        MerceAlPubblico map = miv.getMerceAlPubblico();
        double prezzo = (miv.getMerceAlPubblico().getPrezzo() - ((pp/100)*map.getPrezzo()));
        map.setPromozione(di,df,pp, prezzo);
        merceAlPubblicoRepository.save(map);
    }

    public void rimuoviPromozione(List<MerceInventarioNegozio> lista) {
        for(MerceInventarioNegozio miv : lista){
            miv.getMerceAlPubblico().getPromozione().setDisponibile(false);
            merceAlPubblicoRepository.save(miv.getMerceAlPubblico());
        }
    }

    public List<MerceInventarioNegozio> filtraPromozioniPerCategoria(Categoria categoria, List<MerceInventarioNegozio> promozioni) {
        List<MerceInventarioNegozio> minList=new ArrayList<>();
        Iterator<MerceInventarioNegozio> minAll = promozioni.iterator();
        while(minAll.hasNext()) {
            MerceInventarioNegozio min= minAll.next();
            if(min.getMerceAlPubblico().getMerce().getCategoria().equals(categoria)){
                minList.add(min);
            }
        }
        return minList;
    }

}
