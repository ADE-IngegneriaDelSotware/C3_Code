package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.ClienteRepository;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.personale.Cliente;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class GestoreClienti {

    private Cliente cliente;
    private NegozioRepository negozioRepository;
    private ClienteRepository clienteRepository;
    private GestoreMerci gestoreMerci;

    public GestoreClienti(NegozioRepository negozioRepository, ClienteRepository clienteRepository, GestoreMerci gestoreMerci) {
        this.negozioRepository = negozioRepository;
        this.clienteRepository = clienteRepository;
        this.gestoreMerci = gestoreMerci;
    }

    /*****************Ricerca Prodotto******************/

    public List<Negozio> ricercaProdotto(String nomeProdotto) {
        List<Negozio> listaNegozi = new ArrayList<>();
        Iterator<Negozio> negozioIterator = negozioRepository.findAll().iterator();
        while (negozioIterator.hasNext()) {
            Negozio negozio = negozioIterator.next();
            Iterator<MerceInventarioNegozio> merceInventarioNegozioIterator = negozio.getMerceInventarioNegozio().iterator();
            while (merceInventarioNegozioIterator.hasNext()) {
                MerceInventarioNegozio min = merceInventarioNegozioIterator.next();
                if (min.getMerceAlPubblico().getMerce().getNome().contains(nomeProdotto)) {
                    listaNegozi.add(negozio);
                }
            }
        }
        return listaNegozi;
    }

    /*****************Consulta Promozioni******************/
    public List<MerceInventarioNegozio> getPromozioni() {
        List<MerceInventarioNegozio> min = new ArrayList<>();
        for(Negozio negozio : negozioRepository.findAll()){
            min.addAll(gestoreMerci.getPromozioniAttive(negozio));
        }
        return min;
    }

    public List<MerceInventarioNegozio> filtraPromozioniPerCategoria(Categoria categoria) {
        List<MerceInventarioNegozio> minList=new ArrayList<>();
        Iterator<MerceInventarioNegozio> minAll = getPromozioni().iterator();
        while(minAll.hasNext()) {
            MerceInventarioNegozio min= minAll.next();
            if(min.getMerceAlPubblico().getMerce().getCategoria().equals(categoria)){
                minList.add(min);
            }
        }
        return minList;
    }


    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
}
