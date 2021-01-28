package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.persistenza.ClienteRepository;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.persistenza.RuoloRepository;
import it.unicam.ids.c3.personale.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GestoreAmministratore {

    private Amministratore amministratore;
    private final ClienteRepository clienteRepository;
    private final RuoloRepository ruoloRepository;
    private final NegozioRepository negozioRepository;

    @Autowired
    public GestoreAmministratore(ClienteRepository clienteRepository, RuoloRepository ruoloRepository, NegozioRepository negozioRepository) {
        this.clienteRepository = clienteRepository;
        this.ruoloRepository = ruoloRepository;
        this.negozioRepository = negozioRepository;
    }

    public void setAmministratore(Amministratore amministratore){
        this.amministratore = amministratore;
    }

    public Cliente ricercaCliente(String email) {
        Optional<Cliente> cliente=clienteRepository.findByEmail(email);
        return cliente.orElse(null);
    }

    public void registraCorriere(Cliente cliente,String nomeDitta,String piva,String indirizzo) {
        Corriere corriere = new Corriere(RuoloSistema.CORRIERE,nomeDitta,indirizzo,piva);
        cliente.setRuolo(corriere);
        ruoloRepository.save(corriere);
        clienteRepository.save(cliente);
    }
    public void registraNegozio(List<Categoria> categorie, Cliente cliente, String nome, String piva, String indirizzo) {
        Negozio negozio=new Negozio(nome,indirizzo,piva,categorie);
        Commerciante commerciante=new Commerciante(RuoloSistema.COMMERCIANTE);
        cliente.setRuolo(commerciante);
        negozio.addAddettoNegozio(commerciante);
        ruoloRepository.save(commerciante);
        clienteRepository.save(cliente);
        negozioRepository.save(negozio);
    }
}
