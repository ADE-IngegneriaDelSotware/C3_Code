package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.negozio.Carta;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.persistenza.CartaRepository;
import it.unicam.ids.c3.personale.Cliente;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;

@Service
@Transactional
public class GestoreCarte {

    private CartaRepository cartaRepository;

    public GestoreCarte(CartaRepository cartaRepository) {
        this.cartaRepository = cartaRepository;
    }

    public long assegnaCarta(Cliente cliente, TipoScontoCliente tsc, Negozio negozio){
        Carta carta= new Carta(cliente,tsc);
        generateCodCarta(carta,negozio);
        cartaRepository.save(carta);
        negozio.addCarta(carta);
        return carta.getCodice();
    }

    public void generateCodCarta(Carta carta,Negozio negozio){
        long rand = carta.createCodice();
        Iterator<Carta> carte = negozio.getCarte().iterator();
        while(carte.hasNext()){
            if(carte.next().getCodice() == rand){
                generateCodCarta(carta,negozio);
            }
        }
        carta.setCodice(rand);
    }

    public boolean verificaCodiceCarta(long cc , Negozio negozio) {
        if(!negozio.getCarte().isEmpty()) {
            Iterator<Carta> it = negozio.getCarte().iterator();
            while (it.hasNext()) {
                Carta c = it.next();
                if (c.getCodice() == cc) {
                    return true;
                }
            }
        }
        return false;
    }

    public long searchCodiceCartaFromEmail(String email,Negozio negozio) {
        if(!negozio.getCarte().isEmpty()) {
            Iterator<Carta> it = negozio.getCarte().iterator();
            while (it.hasNext()) {
                Carta c = it.next();
                if (c.getCliente().getEmail().equals(email)) {
                    return c.getCodice();
                }
            }
        }
        return 0;
    }

    public Carta searchCarta(long cc,Negozio negozio) {
        if(!negozio.getCarte().isEmpty()){
            Iterator<Carta> carte = negozio.getCarte().iterator();
            while(carte.hasNext()){
                Carta carta = carte.next();
                if(carta.getCodice() == cc){
                    return carta;
                }
            }
        }
        return null;
    }

    public double calcolaScontoCarta(long cc,Negozio negozio) {
        if(!negozio.getCarte().isEmpty()){
            Iterator<Carta> carte = negozio.getCarte().iterator();
            while(carte.hasNext()){
                Carta carta = carte.next();
                if(carta.getCodice() == cc){
                    return carta.getSconto();
                }
            }
        }
        return 0;
    }
}
