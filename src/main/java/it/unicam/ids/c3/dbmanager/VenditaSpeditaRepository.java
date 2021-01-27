package it.unicam.ids.c3.dbmanager;

import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaSpeditaRepository extends JpaRepository<VenditaSpedita, Long> {
}
