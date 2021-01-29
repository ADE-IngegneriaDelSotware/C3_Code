package it.unicam.ids.c3.gestori;


import it.unicam.ids.c3.merce.*;
import it.unicam.ids.c3.negozio.Carta;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.persistenza.*;
import it.unicam.ids.c3.personale.*;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class GestoreCommerciante {

    private Commerciante commerciante;
    private Negozio negozio;
    private RuoloRepository ruoloRepository;
    private ClienteRepository clienteRepository;
    private CartaRepository cartaRepository;
    private VenditaSpeditaRepository venditaSpeditaRepository;
    private MerceInventarioNegozioRepository merceInventarioNegozioRepository;
    private MerceAlPubblicoRepository merceAlPubblicoRepository;
    private CorriereRepository corriereRepository;
    private NegozioRepository negozioRepository;
    private MerceRepository merceRepository;

    public GestoreCommerciante(RuoloRepository ruoloRepository, ClienteRepository clienteRepository, CartaRepository cartaRepository, VenditaSpeditaRepository venditaSpeditaRepository, MerceInventarioNegozioRepository merceInventarioNegozioRepository, MerceAlPubblicoRepository merceAlPubblicoRepository, CorriereRepository corriereRepository, NegozioRepository negozioRepository, MerceRepository merceRepository) {
        this.ruoloRepository = ruoloRepository;
        this.clienteRepository = clienteRepository;
        this.cartaRepository = cartaRepository;
        this.venditaSpeditaRepository = venditaSpeditaRepository;
        this.merceInventarioNegozioRepository = merceInventarioNegozioRepository;
        this.merceAlPubblicoRepository = merceAlPubblicoRepository;
        this.corriereRepository = corriereRepository;
        this.negozioRepository = negozioRepository;
        this.merceRepository = merceRepository;
    }

    /*****************Assegnazione Carta***************/

    public Cliente getCliente(String email){
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if(cliente.isPresent()){
            return cliente.get();
        }
        throw new IllegalStateException("cliente non presente");
    }

    public long assegnaCarta(Cliente cliente, TipoScontoCliente tsc){
        Carta carta= new Carta(cliente,tsc);
        generateCodCarta(carta);
        cartaRepository.save(carta);
        getNegozio().addCarta(carta);
        negozioRepository.save(negozio);
        return carta.getCodice();
    }

    public void generateCodCarta(Carta carta){
        long rand = carta.createCodice();
        Iterator<Carta> carte = getNegozio().getCarte().iterator();
        while(carte.hasNext()){
            if(carte.next().getCodice() == rand){
                generateCodCarta(carta);
            }
        }
        carta.setCodice(rand);
    }

    /***************Fine Assegnazione Carta*******************/


    /*********Consulta Inventario****************/

    public List<MerceInventarioNegozio> getInventario() {
        return negozio.getMerceInventarioNegozio();
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

    /******************* Fine consulta Inventario **************************/

    /************Consegna Vendita Assegnata******************/

    public List<VenditaSpedita> getAcquistiClienteDaRitirare(String email) {
        List<VenditaSpedita> list = new ArrayList<>();
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if(cliente.isPresent()){
            if(!cliente.get().getAcquisti().isEmpty()){
                Iterator<VenditaSpedita> venditeNegozio = getNegozio().getVenditeNegozioRitiro().iterator();
                while(venditeNegozio.hasNext()){
                    VenditaSpedita vs = venditeNegozio.next();
                    if(vs.getStatoConsegna().equals(StatoConsegna.CONSEGNATO_AL_NEGOZIO)){
                        Iterator<Vendita> venditaIterator = cliente.get().getAcquisti().iterator();
                        while(venditaIterator.hasNext()){
                            Vendita vendita = venditaIterator.next();
                            if(vendita.getId() == vs.getId()){
                                list.add(vs);
                            }
                        }

                    }
                }
            }
        }
        return list;
    }

    public void confermaConsegnaVenditaAssegnata(List<VenditaSpedita> vendite) {
        aggiornaStatoVendita(vendite,StatoConsegna.CONSEGNATO_AL_CLIENTE);
        venditaSpeditaRepository.saveAll(vendite);
        //TODO: forse c'è da rimettere le due righe sotto
        //getNegozio().removeVenditeInNegozioRitiro(vendite);
        //negozioRepository.save(getNegozio());
    }

    public void aggiornaStatoVendita(List<VenditaSpedita> list, StatoConsegna sc) {
        Iterator<VenditaSpedita> iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next().setStatoConsegna(sc);
        }
    }
    /************************ Fine Consegna Vendita Assegnata**********************/


    /*****************GestionePromozioni*****************/

    public List<MerceInventarioNegozio> getPromozioniAttive() {
        List<MerceInventarioNegozio> list = new ArrayList<>();
        if(!getNegozio().getMerceInventarioNegozio().isEmpty()){
            Iterator<MerceInventarioNegozio> iterator = getNegozio().getMerceInventarioNegozio().iterator();
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

    public List<MerceInventarioNegozio> getPromozioniPossibili() {
        List<MerceInventarioNegozio> list = new ArrayList<>();
        if(!getNegozio().getMerceInventarioNegozio().isEmpty()){
            Iterator<MerceInventarioNegozio> iterator = getNegozio().getMerceInventarioNegozio().iterator();
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
        }
    }

    /****************Gestione corrieri*******************/

    public List<Corriere> getCorrieri() {
        List<Corriere> lc=new ArrayList<>();
        List<Corriere> listaCorretta = new ArrayList<>();
        lc.addAll(corriereRepository.findAll());
        Iterator<Corriere> corriereIterator = getNegozio().getCorrieri().iterator();
        while (corriereIterator.hasNext()){
            Corriere corriere = corriereIterator.next();
            Optional<Corriere> corriereOptional = corriereRepository.findById(corriere.getId());
            if(corriereOptional.isPresent()){
                listaCorretta.add(corriereOptional.get());
            }
        }
        lc.removeAll(listaCorretta);
        return lc;
    }

    public void addCorrieri(List<Corriere> corrieriDaAggiungere) {
        Iterator<Corriere> corriereIterator = corrieriDaAggiungere.iterator();
        while(corriereIterator.hasNext()){
            Corriere corriere = corriereIterator.next();
            getNegozio().addCorriere(corriere);
        }
        negozioRepository.save(negozio);
    }

    /****************Assunzione Addetto*******************/

    public void assunzioneAddetto(Cliente cliente){
        AddettoNegozio addettoNegozio = new AddettoNegozio(RuoloSistema.ADDETTONEGOZIO);
        cliente.setRuolo(addettoNegozio);
        ruoloRepository.save(addettoNegozio);
        clienteRepository.save(cliente);
        getNegozio().addAddettoNegozio(addettoNegozio);
        negozioRepository.save(negozio);
    }

    /***************Gestione Inventario********************/

    public void addMerce(String nome, String descrizione, Categoria categoria, double quantita, double prezzo, double sconto) {
        Merce merce = new Merce(nome,categoria,descrizione);
        merceRepository.save(merce);
        MerceAlPubblico mp = new MerceAlPubblico(prezzo,merce,sconto);
        merceAlPubblicoRepository.save(mp);
        MerceInventarioNegozio min = new MerceInventarioNegozio(quantita,mp);
        merceInventarioNegozioRepository.save(min);
        getNegozio().addMerceInventarioNegozio(min);
        negozioRepository.save(getNegozio());
    }

    public void removeMerce(List<MerceInventarioNegozio> list, double quantita){
        Iterator<MerceInventarioNegozio> minIterator=getNegozio().getMerceInventarioNegozio().iterator();
        while (minIterator.hasNext()) {
            MerceInventarioNegozio min=minIterator.next();
            Iterator<MerceInventarioNegozio> daRimuovere = list.iterator();
            while(daRimuovere.hasNext()) {
                MerceInventarioNegozio minDaRimuovere= daRimuovere.next();
                if(min.equals(minDaRimuovere)) {
                    double nuovaQuantita=min.getQuantita()-quantita;
                    min.setQuantita(nuovaQuantita);
                }
            }
        }
        negozioRepository.save(negozio);
    }

    public List<MerceInventarioNegozio> getMerciInventarioNegozio() {
        return getNegozio().getMerceInventarioNegozio();
    }

    /************************** Fine gestione inventario ******************************/



    public Negozio getNegozio() {
        return this.negozio;
    }

    public void setCommerciante(Commerciante commerciante){
        this.commerciante = commerciante;
    }

    public void setNegozio(Negozio negozio){
        this.negozio = negozio;
    }
}
