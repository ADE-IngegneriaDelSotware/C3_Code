package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ControllerAccesso {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ControllerAccesso(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public boolean accesso(String email, String password) {
        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
        if (cliente.isPresent()) {
            if(cliente.get().getPassword().equals(password)){
                System.out.println("accesso ultimate");
                return true;
            }
            System.out.println("password errata");
            return false;
        }
        System.out.println("credenziali errate");
        return false;
    }
//        Optional<Cliente> cliente = clienteRepository.findByEmail(email);
//        if (cliente.isPresent()) {
//            if(cliente.get().getPassword().equals(password)){
//                return "accesso ultimate";
//            }
//            return "password errata";
//        }
//        return "credenziali errate";

//            if(cliente.getEmail().equals(email) && cliente.getPassword().equals(password)){
//                System.out.println(cliente.getNome());
//            }
//            if(cliente.getPassword().equals(password) && cliente.getUserName().equals(userName)){
//                if(cliente.getRuolo()!=null){
//                    switch (cliente.getRuolo().getRuoloSistema()){
//                        case CORRIERE: cc.setCorriere((Corriere) cliente.getRuolo());
//                            break;
//                        case AMMINISTRATORE:
//                            ca.setAdmin((Amministratore) cliente.getRuolo());
//                            break;
//                        case ADDETTONEGOZIO:
//                        case COMMERCIANTE:
//                            Iterator<Negozio> iterator = gn.getNegozi().iterator();
//                            while(iterator.hasNext()){
//                                Negozio negozio = iterator.next();
//                                Iterator<AddettoNegozio> addettoNegozioIterator = negozio.getAddetti().iterator();
//                                while (addettoNegozioIterator.hasNext()){
//                                    AddettoNegozio an = addettoNegozioIterator.next();
//                                    if(an.equals((cliente.getRuolo()))){
//                                        cn.setNegozio(negozio);
//                                    }
//                                }
//                            }
//                            break;
//                    }
//                } else {
//                    ccl.setCliente(cliente);
//                }
//                return cliente;
//            }



}
