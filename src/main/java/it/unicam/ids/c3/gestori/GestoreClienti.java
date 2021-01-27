package it.unicam.ids.c3.gestori;

import it.unicam.ids.c3.dbmanager.ClienteRepository;
import it.unicam.ids.c3.personale.Cliente;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GestoreClienti {

    private Cliente cliente;


    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
}
