package it.unicam.ids.c3.gestori;


import it.unicam.ids.c3.merce.*;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.persistenza.*;
import it.unicam.ids.c3.personale.*;
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

    private GestoreCheckout gestoreCheckout;
    private GestoreCarte gestoreCarte;
    private GestoreVendite gestoreVendite;
    private GestoreMerci gestoreMerci;
    private Negozio negozio;
    private RuoloRepository ruoloRepository;
    private ClienteRepository clienteRepository;
    private MerceInventarioNegozioRepository merceInventarioNegozioRepository;
    private MerceAlPubblicoRepository merceAlPubblicoRepository;
    private CorriereRepository corriereRepository;
    private NegozioRepository negozioRepository;
    private MerceRepository merceRepository;


    public GestoreCommerciante(GestoreCheckout gestoreCheckout, GestoreCarte gestoreCarte, GestoreVendite gestoreVendite, GestoreMerci gestoreMerci, RuoloRepository ruoloRepository, ClienteRepository clienteRepository, MerceInventarioNegozioRepository merceInventarioNegozioRepository, MerceAlPubblicoRepository merceAlPubblicoRepository, CorriereRepository corriereRepository, NegozioRepository negozioRepository, MerceRepository merceRepository) {
        this.gestoreCheckout = gestoreCheckout;
        this.gestoreCarte = gestoreCarte;
        this.gestoreVendite = gestoreVendite;
        this.gestoreMerci = gestoreMerci;
        this.ruoloRepository = ruoloRepository;
        this.clienteRepository = clienteRepository;
        this.merceInventarioNegozioRepository = merceInventarioNegozioRepository;
        this.merceAlPubblicoRepository = merceAlPubblicoRepository;
        this.corriereRepository = corriereRepository;
        this.negozioRepository = negozioRepository;
        this.merceRepository = merceRepository;
    }

    /********** Checkout Merce *********/

    public void startCarrello(){ gestoreCheckout.startCarrello(); }

    public double getPrezzo(long id, double quantita) {
        return gestoreCheckout.getPrezzo(id,quantita,getNegozio());
    }

    public double getSconto(long id) {
        return gestoreCheckout.getSconto(id,getNegozio());
    }

    public double aggiuntaMerceNelCarrello(double prezzo, double sconto, long id, double quantita) {
        return gestoreCheckout.aggiuntaMerceNelCarrello(prezzo, sconto , id, quantita , getNegozio());
    }

    public double calcoraResto(double denaro) {
        return gestoreCheckout.calcoraResto(denaro);
    }

    public void checkoutCompletato() {
        gestoreCheckout.checkoutCompletato();
    }

    public void annullaCheckout() {
        gestoreCheckout.annullaCheckout(getNegozio());
    }

    public double applyScontoCarta(long cc) {
        return gestoreCheckout.applyScontoCarta(cc, getNegozio());
    }

    public void addVenditaInventario() {
        gestoreCheckout.addVenditaInventario(getNegozio());
    }

    /****************Richiesta Carta*******************/

    public boolean verificaCodiceCarta(long cc) {
        return gestoreCheckout.verificaCodiceCarta(cc,getNegozio());
    }

    public long searchCodiceCartaFromEmail(String email) {
        return gestoreCheckout.searchCodiceCartaFromEmail(email, getNegozio());
    }

    /*****************Registra Vendita********************/

    public List<Corriere> getCorrieriDisponibili() {
        return gestoreCheckout.getCorrieriDisponibili(getNegozio());
    }

    public List<Negozio> getNegoziDisponibili() {
        return gestoreCheckout.getNegoziDisponibili(getNegozio());
    }

    public void registraAcquistoCliente(long cc, Negozio pdr, String indirizzo, Corriere cr) {
        gestoreCheckout.registraAcquistoCliente(cc, pdr, indirizzo, cr, getNegozio());
    }

    public void registraAcquistoCliente(long cc) {
        gestoreCheckout.registraAcquistoCliente(cc, getNegozio());
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
        return gestoreCarte.assegnaCarta(cliente,tsc, getNegozio());
    }

    /*********Consulta Inventario****************/

    public List<MerceInventarioNegozio> getInventario() {
        return negozio.getMerceInventarioNegozio();
    }

    public String getInfoMerce(MerceInventarioNegozio min) {
        return gestoreMerci.getInfoMerce(min);
    }

    /************Consegna Vendita Assegnata******************/

    public List<VenditaSpedita> getAcquistiClienteDaRitirare(String email) {
        return gestoreVendite.getAcquistiClienteDaRitirare(email, getNegozio());
    }

    public void confermaConsegnaVenditaAssegnata(List<VenditaSpedita> vendite) {
        gestoreVendite.confermaConsegnaVenditaAssegnata(vendite);
    }

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

    public void setNegozio(Negozio negozio){
        this.negozio = negozio;
    }
}
