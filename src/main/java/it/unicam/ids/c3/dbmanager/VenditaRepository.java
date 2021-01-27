package it.unicam.ids.c3.dbmanager;

import it.unicam.ids.c3.vendita.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditaRepository extends JpaRepository<Vendita, Long> {
}
