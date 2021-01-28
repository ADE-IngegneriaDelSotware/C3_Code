package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

}
