package it.unicam.ids.c3.gestori;


import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Carta;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.persistenza.ClienteRepository;
import it.unicam.ids.c3.persistenza.VenditaSpeditaRepository;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.Commerciante;
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
public class GestoreCommerciante {

    private Commerciante commerciante;
    private Negozio negozio;
    private ClienteRepository clienteRepository;
    private VenditaSpeditaRepository venditaSpeditaRepository;

    public GestoreCommerciante(ClienteRepository clienteRepository, VenditaSpeditaRepository venditaSpeditaRepository) {
        this.clienteRepository = clienteRepository;
        this.venditaSpeditaRepository = venditaSpeditaRepository;
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

    /******************GestioneInventario********************/

    public void addMerce(String nome, String descrizione, Categoria categoria, double quantita, double prezzo, int sconto) {
        Merce merce = new Merce(nome,categoria,descrizione);
        MerceAlPubblico mp = new MerceAlPubblico(prezzo,merce,sconto);
        MerceInventarioNegozio min = new MerceInventarioNegozio(quantita,mp);
        negozio.addMerceInventarioNegozio(min);
    }

    public void removeMerce(List<MerceInventarioNegozio> list, double quantita){
        for(MerceInventarioNegozio min : list){
            min.setQuantita(min.getQuantita() - quantita);
        }
    }

    public Negozio getNegozio() {
        return negozio;
    }

    public void setCommerciante(Commerciante commerciante){
        this.commerciante = commerciante;
    }

    public void setNegozio(Negozio negozio){
        this.negozio = negozio;
    }
}
