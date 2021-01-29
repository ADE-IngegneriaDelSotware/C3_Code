package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Carta;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.persistenza.*;
import it.unicam.ids.c3.personale.AddettoNegozio;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.StatoConsegna;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GestoreAddetto {

    private AddettoNegozio addettoNegozio;
    private Negozio negozio;
    private NegozioRepository negozioRepository;
    private List<MerceVendita> merciCarrello;
    private double prezzoCarrello;
    private ClienteRepository clienteRepository;
    private VenditaSpeditaRepository venditaSpeditaRepository;
    private MerceRepository merceRepository;
    private MerceAlPubblicoRepository merceAlPubblicoRepository;
    private MerceVenditaRepository merceVenditaRepository;

    public GestoreAddetto(NegozioRepository negozioRepository, ClienteRepository clienteRepository, VenditaSpeditaRepository venditaSpeditaRepository, MerceRepository merceRepository, MerceAlPubblicoRepository merceAlPubblicoRepository, MerceVenditaRepository merceVenditaRepository) {
        this.negozioRepository = negozioRepository;
        this.clienteRepository = clienteRepository;
        this.venditaSpeditaRepository = venditaSpeditaRepository;
        this.merceRepository = merceRepository;
        this.merceAlPubblicoRepository = merceAlPubblicoRepository;
        this.merceVenditaRepository = merceVenditaRepository;
        this.merciCarrello = new ArrayList<>();
        this.prezzoCarrello = 0;
    }

    public Negozio getNegozio(){
        return this.negozio;
    }

    /********** Checkout Merce *********/

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
        return searchPrezzo(id)*quantita;
    }

    public double searchPrezzo(long id) {
        Iterator<MerceInventarioNegozio> min = getNegozio().getMerceInventarioNegozio().iterator();
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

    public double getSconto(long id) {
        Iterator<MerceInventarioNegozio> it = getNegozio().getMerceInventarioNegozio().iterator();
        while (it.hasNext()){
            MerceInventarioNegozio min = it.next();
            if(min.getMerceAlPubblico().getMerce().getID()==id){
                return min.getMerceAlPubblico().getSconto();
            }
        }
        return 0;
    }

    public double calcolaPrezzoMerce(double prezzo,double sconto) {
        double price = prezzo-((sconto/100)*prezzo);
        return price;
    }

    public double calcolaPrezzoTotale(double prezzo,double sconto) {
        return prezzoCarrello + calcolaPrezzoMerce(prezzo,sconto);
    }

    public void scaloQuantita(MerceAlPubblico mp , double quantita){
        for(MerceInventarioNegozio min : getNegozio().getMerceInventarioNegozio()){
            if(min.getMerceAlPubblico().equals(mp)){
                min.setQuantita(min.getQuantita()-quantita);
            }
        }
    }

    public double aggiuntaMerceNelCarrello(double prezzo, double sconto, long id, double quantita) {
        MerceAlPubblico mp = getMerce(id,prezzo,quantita);



        scaloQuantita(mp,quantita);
        this.prezzoCarrello = calcolaPrezzoTotale(prezzo,sconto);
        MerceVendita mv = new MerceVendita(prezzo - ((sconto/100)*prezzo),quantita,mp);
        merceVenditaRepository.save(mv);
        addMerceCarrello(mv);
        return this.prezzoCarrello;
    }

    public MerceAlPubblico getMerce(long id, double prezzo, double quantita) {
        Iterator<MerceInventarioNegozio> it = getNegozio().getMerceInventarioNegozio().iterator();
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

    /****************Richiesta Carta*******************/

    public boolean verificaCodiceCarta(long cc) {
        if(!getNegozio().getCarte().isEmpty()) {
            Iterator<Carta> it = getNegozio().getCarte().iterator();
            while (it.hasNext()) {
                Carta c = it.next();
                if (c.getCodice() == cc) {
                    return true;
                }
            }
        }
        return false;
    }

    public long searchCodiceCartaFromCodiceFiscale(String cf) {
        if(!getNegozio().getCarte().isEmpty()) {
            Iterator<Carta> it = getNegozio().getCarte().iterator();
            while (it.hasNext()) {
                Carta c = it.next();
                if (c.getCliente().getEmail().equals(cf)) {
                    return c.getCodice();
                }
            }
        }
        return 0;
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
        getNegozio().addCarta(carta);
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
        return getNegozio().getMerceInventarioNegozio();
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

    public void setAddettoNegozio(AddettoNegozio addettoNegozio){
        this.addettoNegozio = addettoNegozio;
    }

    public void setNegozio(Negozio negozio){
        this.negozio = negozio;
    }

}
